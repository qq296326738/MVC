package com.zzq.mybatis;

/**
 * 执行器
 */
public interface Excutor {

    public <T> T query(String statement, Object parameter);

}
