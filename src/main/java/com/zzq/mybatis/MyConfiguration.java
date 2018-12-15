package com.zzq.mybatis;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 读取与解析配置类,并返回
 */
public class MyConfiguration {

    private static ClassLoader loader = ClassLoader.getSystemClassLoader();

    public Connection build(String resource) throws DocumentException, SQLException, ClassNotFoundException {
        InputStream stream = loader.getResourceAsStream(resource);
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(stream);
        Element root = document.getRootElement();
        return evalDadaSource(root);
    }

    private Connection evalDadaSource(Element node) throws ClassNotFoundException, SQLException {
        if (!node.getName().equals("database")) {
            throw new RuntimeException("xml配置失败");
        }
        String driverClassName = null;
        String url = null;
        String username = null;
        String password = null;
        for (Object element : node.elements("properties")) {
            Element i = (Element) element;
            String value = getValue(i);
            String name = i.attributeValue("name");
            if (name == null || value == null) {
                throw new RuntimeException("读取失败");
            }
            switch (name) {
                case "driverClassName":
                    driverClassName = value;
                    break;
                case "url":
                    url = value;
                    break;
                case "username":
                    username = value;
                    break;
                case "password":
                    password = value;
                    break;
                default:
                    throw new RuntimeException("配置参数异常");
            }

        }
        Class.forName(driverClassName);
        return DriverManager.getConnection(url, username, password);
    }

    private String getValue(Element node) {
        return node.hasContent() ? node.getText() : node.attributeValue("value");
    }

    public MapperBean readMapper(String path) throws DocumentException {
        MapperBean mapperBean = new MapperBean();
        InputStream resourceAsStream = loader.getResourceAsStream(path);
        SAXReader reader = new SAXReader();
        Document read = reader.read(resourceAsStream);
        Element rootElement = read.getRootElement();
        String nameSpace = rootElement.attributeValue("nameSpace").trim();
        mapperBean.setInterfaceName(nameSpace);
        ArrayList<Function> list = new ArrayList<>();
        for (Iterator iterator = rootElement.elementIterator(); iterator.hasNext(); ) {
            Function function = new Function();
            Element next = (Element) iterator.next();
            String sqlType = next.getName().trim();
            String funcName = next.attributeValue("id");
            String sql = next.getText();
            String resultType = next.attributeValue("resultType");
            function.setSqlType(sqlType);
            function.setFuncName(funcName);
            function.setSql(sql);
            Object o = null;
            try {
                o = Class.forName(resultType);

            } catch (Exception e) {
                e.printStackTrace();
            }
            function.setResultType(o);
            list.add(function);
        }
        mapperBean.setList(list);
        return mapperBean;
    }

}
