package org.vtb.practice.task03;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CachingHandler implements InvocationHandler {
    private Object objectCached;
    private CalledMutator lastMutator;
    private Map<CalledMutator, Map<Method, CashedObject>> cashes = new ConcurrentHashMap<>();
    private LocalGarbageMonitor localGarbageMonitor;
    private long periodGarbageMonitor;

    private class LocalGarbageCollector extends Thread {
        @Override
        public void run() {
            for (CalledMutator mutator : cashes.keySet()) {
                for (Method method : cashes.get(mutator).keySet()) {
                    if (cashes.get(mutator).get(method).expiredTime < System.currentTimeMillis()) cashes.get(mutator).remove(method);
                }
                if (cashes.get(mutator).isEmpty()) cashes.remove(mutator);
            }
        }
    }

    private class LocalGarbageMonitor extends Thread {
        Thread collector;
        private boolean needCollectGarbage() {
            Map<CalledMutator, Map<Method, CashedObject>> copiedCashes = new ConcurrentHashMap<>(cashes);
            int expiredCahsCounter = 0;
            // Допустим наши правила очистки кэша:
            // 1) Либо есть очень старый кэш, который пережил, например, 3 мониторинга
            // 2) Либо скопилось много мусора, например, 3 бесполезных кэша
            for (Map<Method, CashedObject> cash : copiedCashes.values()) {
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
            collector = new Thread();
            while (true) {
                // Если по какой-то причине очистка мусора не успела завершится, дождёмся её
                if (collector.isAlive()) {
                    try {
                        collector.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
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

        periodGarbageMonitor = 2000;
        localGarbageMonitor = new LocalGarbageMonitor();
        localGarbageMonitor.start();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean cacheAnnotation = false;
        boolean mutatorAnnotation = false;
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

        // Пытаемся вернуть кэш и продлеваем его
        if (cashes.containsKey(lastMutator) && cashes.get(lastMutator).containsKey(method)) {
            cashes.get(lastMutator).get(method).expiredTime = System.currentTimeMillis() + lifeTime;
            return cashes.get(lastMutator).get(method).cashe;
        }

        // Если мы здесь, значит кэша не нашлось,
        // поэтому выполним операцию и, при необходимости, закэшируем значение
        Object res = method.invoke(objectCached, args);

        if (cacheAnnotation) {
            CashedObject cashedObject = new CashedObject(res, System.currentTimeMillis() + lifeTime);

            if (!cashes.containsKey(lastMutator)) {
                Map<Method, CashedObject> curCashe = new ConcurrentHashMap<>();
                curCashe.put(method, cashedObject);
                cashes.put(lastMutator, curCashe);
            } else cashes.get(lastMutator).put(method, cashedObject);
        }

        return res;
    }
}
