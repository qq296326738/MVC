package com.zzq.mybatis;

import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyExcutor implements Excutor {

    private MyConfiguration config = new MyConfiguration();

    @Override
    public <T> T query(String sql, Object parameter) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, Long.valueOf(parameter.toString()));
            resultSet = preparedStatement.executeQuery();
            UserDome userDome = new UserDome();
            while (resultSet.next()) {
                userDome.setId(resultSet.getLong(1));
                userDome.setName(resultSet.getString(2));
            }
            return (T) userDome;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private Connection getConnection() {
        try {
            return config.build("mybatis-config.xml");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
