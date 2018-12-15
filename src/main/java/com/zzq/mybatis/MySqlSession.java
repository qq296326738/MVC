package com.zzq.mybatis;

import java.lang.reflect.Proxy;

public class MySqlSession {

    private Excutor excutor = new MyExcutor();

    private MyConfiguration configuration = new MyConfiguration();

    public <T> T selectOne(String statement, Object paramrter) {
        return excutor.query(statement, paramrter);
    }

    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz},
                new MyMapperProxy(configuration, this));
    }

}
