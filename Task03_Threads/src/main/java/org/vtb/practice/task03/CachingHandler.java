package org.vtb.practice.task03;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CachingHandler implements InvocationHandler {
    private Object objectCached;
    private CalledMutator lastMutator;
//    private Map<Method, CachedObject> curStateCaches;
    private Map<CalledMutator, Map<Method, CachedObject>> caches = new ConcurrentHashMap<>();
    private LocalGarbageMonitor localGarbageMonitor;
    private long periodGarbageMonitor;

    private class LocalGarbageCollector extends Thread {
        @Override
        public void run() {
            for (CalledMutator mutator : caches.keySet()) {
                for (Method method : caches.get(mutator).keySet()) {
                    if (caches.get(mutator).get(method).expiredTime < System.currentTimeMillis()) caches.get(mutator).remove(method);
                }
                if (caches.get(mutator).isEmpty()) caches.remove(mutator);
            }
        }
    }

    private class LocalGarbageMonitor extends Thread {
        private boolean needCollectGarbage() {
            Map<CalledMutator, Map<Method, CachedObject>> copiedCaches = new ConcurrentHashMap<>(caches);
            int expiredCahsCounter = 0;
            // Допустим наши правила очистки кэша:
            // 1) Либо есть очень старый кэш, который пережил, например, 3 мониторинга
            // 2) Либо скопилось много мусора, например, 3 бесполезных кэша
            for (Map<Method, CachedObject> cash : copiedCaches.values()) {
                for (Method method : cash.keySet()) {
                    long expiredTime = cash.get(method).expiredTime;

                    if (expiredTime + 3 * periodGarbageMonitor < System.currentTimeMillis()) return true;

                    if (expiredTime < System.currentTimeMillis()) expiredCahsCounter++;
                    if (expiredCahsCounter >= 3) return true;
                }
            }
            return false;
        }
        @Override
        public void run (){
            Thread collector = new LocalGarbageCollector();
            while (true) {
                // Если по какой-то причине очистка мусора не успела завершится, дождёмся её
                if (collector.isAlive()) {
                    try {
                        collector.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                // Нет кэшей - нет мониторинга
                // мониторинг запустится при обращении к аннотированным(Cache) методам объекта.
                if (caches.isEmpty()) break;

                if (needCollectGarbage()) {
                    collector = new LocalGarbageCollector();
                    collector.start();
                }

                try {
                    Thread.sleep(periodGarbageMonitor);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    private CachingHandler() {}

    public <T> CachingHandler(T objectIncome) {
        this.objectCached = objectIncome;
        this.lastMutator = new CalledMutator();
//        this.curStateCaches = new ConcurrentHashMap<>();

        periodGarbageMonitor = 2000;
        localGarbageMonitor = new LocalGarbageMonitor();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean cacheAnnotation = false;
        boolean mutatorAnnotation = false;
        Map<Method, CachedObject> curStateCaches;
        long lifeTime = 0;

        Method methodObj = objectCached.getClass().getMethod(method.getName(), method.getParameterTypes());
        Annotation[] annotationArray = methodObj.getAnnotations();

        for (Annotation annotation : annotationArray) {
            if (annotation.annotationType().equals(Cache.class)) {
                lifeTime = methodObj.getAnnotation(Cache.class).value();
                cacheAnnotation = true;
            }
            if (annotation.annotationType().equals(Mutator.class)) mutatorAnnotation = true;
        }

        if (mutatorAnnotation) lastMutator = new CalledMutator(method, args);

        if (cacheAnnotation) {
            // Пытаемся вернуть кэш и продлеваем его
            curStateCaches = getCurStateCaches(caches, lastMutator);
            if (curStateCaches != null
                    && curStateCaches.get(method) != null
                    && curStateCaches.get(method).expiredTime > System.currentTimeMillis()
            ) {
                CachedObject savedCache = curStateCaches.get(method);
                curStateCaches.get(method).expiredTime = System.currentTimeMillis() + lifeTime;
                caches.put(lastMutator, curStateCaches);
                return savedCache.cashe;
            }
        }

        // Если мы здесь, значит кэша не нашлось (либо он протухший),
        // поэтому выполним операцию и, при необходимости, закэшируем значение
        Object res = method.invoke(objectCached, args);

        if (cacheAnnotation) {
            CachedObject cachedObject = new CachedObject(res, System.currentTimeMillis() + lifeTime);

            curStateCaches = getCurStateCaches(caches, lastMutator);
            curStateCaches.put(method, cachedObject);
            caches.put(lastMutator, curStateCaches);

            if (!localGarbageMonitor.isAlive()) {
                localGarbageMonitor = new LocalGarbageMonitor();
                localGarbageMonitor.start();
            }
        }

        return res;
    }

    public Map<Method, CachedObject> getCurStateCaches(Map<CalledMutator, Map<Method, CachedObject>> caches, CalledMutator lastMutator) {
        Map<Method, CachedObject> cachedObjectMap = caches.get(lastMutator);
        return cachedObjectMap == null ? new ConcurrentHashMap<>() : new ConcurrentHashMap<>(cachedObjectMap);
    }
}
