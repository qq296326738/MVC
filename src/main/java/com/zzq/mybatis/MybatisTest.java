package com.zzq.mybatis;

public class MybatisTest {
    public static void main(String[] args) {
        MySqlSession mySqlSession = new MySqlSession();
        UserMapper mapper = mySqlSession.getMapper(UserMapper.class);
        UserDome byId = mapper.findById(996593333251497986L);
        System.out.println(byId);
    }
}
