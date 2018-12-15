package com.zzq.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class MyMapperProxy implements InvocationHandler {

    private MyConfiguration configuration;

    private MySqlSession mySqlSession;


    public MyMapperProxy(MyConfiguration configuration, MySqlSession mySqlSession) {
        this.configuration = configuration;
        this.mySqlSession = mySqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperBean mapperBean = configuration.readMapper("userMapper.xml");
        if (!method.getDeclaringClass().getName().equals(mapperBean.getInterfaceName())) {
            return null;
        }
        List<Function> list = mapperBean.getList();
        if (list != null && !list.isEmpty()) {
            for (Function function :list) {
                if (method.getName().equals(function.getFuncName())) {
                    return mySqlSession.selectOne(function.getSql(),args[0]);
                }
            }

        }

        return null;
    }
}
