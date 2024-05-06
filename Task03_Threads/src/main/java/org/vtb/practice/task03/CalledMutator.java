package org.vtb.practice.task03;

import lombok.EqualsAndHashCode;

import java.lang.reflect.Method;

@EqualsAndHashCode
public class CalledMutator {
    Method method;
    Object[] args;

    public CalledMutator(Method method, Object[] args) {
        this.method = method;
        this.args = args;
    }

    public CalledMutator() {
    }
}
