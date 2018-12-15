package com.zzq.common.TestProxy;

public class Test {
    public static void main(String[] args) throws Exception {
        TestInterfaceImpl testInterface = new TestInterfaceImpl();
        InvocationHandler invocationHandler = new InvocationHandler(testInterface);
        TestInterface instance = (TestInterface) Proxy.newProxyInstance(TestInterface.class, invocationHandler);
        instance.function();
    }


}