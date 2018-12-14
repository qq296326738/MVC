package com.zzq.common;

import java.lang.reflect.Method;

public class InvocationHandler {

    private Object o;

    public InvocationHandler(Object o) {
        this.o = o;
    }

    public Object invoke(Object proxy, Method method) throws Throwable {
        System.out.println("+++++++++++++++++");
        Object invoke = method.invoke(o);
        System.out.println("+++++++++++++++++");
        return invoke;
    }
}
