package com.zzq.common;

import java.lang.reflect.Method;

public class $Proxy1 implements com.zzq.common.TestInterface {
    public $Proxy1(InvocationHandler h) {
        this.h = h;
    }

    com.zzq.common.InvocationHandler h;

    @Override
    public void function() {
        try {
            Method md = com.zzq.common.TestInterface.class.getMethod("function");
            h.invoke(this, md);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}