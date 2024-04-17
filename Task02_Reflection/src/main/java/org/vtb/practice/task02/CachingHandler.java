package org.vtb.practice.task02;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CachingHandler implements InvocationHandler {
    private Object objectCached;
    private Map<Method, Object> cachedMethods;

    private CachingHandler() {}

    public <T> CachingHandler(T objectIncome) {
        this.objectCached = objectIncome;
        this.cachedMethods = new HashMap<>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean cacheAnnotation = false;
        Annotation[] annotationArray = objectCached.getClass().getMethod(method.getName(), method.getParameterTypes()).getAnnotations();

        for (Annotation annotation : annotationArray) {
            if (annotation.annotationType().equals(Cache.class)) cacheAnnotation = true;
            if (annotation.annotationType().equals(Mutator.class)) cachedMethods.clear();
        }

        if (cachedMethods.containsKey(method)) return cachedMethods.get(method);

        Object res = method.invoke(objectCached, args);
        if (cacheAnnotation) cachedMethods.put(method, res);
        return res;
    }
}
