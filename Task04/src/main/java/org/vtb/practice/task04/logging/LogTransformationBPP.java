package org.vtb.practice.task04.logging;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class LogTransformationBPP implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(LogTransformation.class)) {
            String logFile = bean.getClass().getAnnotation(LogTransformation.class).value();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                    Object res = method.invoke(bean, args);
                    try {
                        FileWriter fileWriter = new FileWriter(logFile, true);
                        fileWriter.write(dateFormat.format(Calendar.getInstance().getTime())
                                + " > " + "Class:" + bean.getClass().getSimpleName() + " / "
                                + "method:" + method.getName() + "\n"
                                + "\t" + "args:" + "\t" + Arrays.toString(args) + "\n"
                                + "\t" + "result:" + "\t" + res + "\n");
                        fileWriter.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return res;
                }
            });
        }

        return bean;
    }
}
