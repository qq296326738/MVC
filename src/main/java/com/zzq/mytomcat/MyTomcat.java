package com.zzq.mytomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MyTomcat {

    private int port = 9999;

    private Map<String, String> urlServletMap = new HashMap<>();

    public MyTomcat(int port) {
        this.port = port;
    }

    public void start() {
        this.initServletMapping();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("我的TomCat启动");
            while (true) {
                final Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        InputStream inputStream = socket.getInputStream();
                        OutputStream outputStream = socket.getOutputStream();

                        MyRequset myRequset = new MyRequset(inputStream);
                        MyResponse myResponse = new MyResponse(outputStream);
                        //请求转发
                        dispathc(myRequset, myResponse);

                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }).start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initServletMapping() {
        for (ServletMapping servletMapping : ServletMappingConfig.servletMappingList) {
            urlServletMap.put(servletMapping.getUrl(), servletMapping.getCalzz());
        }
    }

    private void dispathc(MyRequset myRequset, MyResponse myResponse) {
        String url = myRequset.getUrl();
        if ("/favicon.ico".equals(url) || url == null) {
            return;
        }
        String clazz = urlServletMap.get("/");
        if (clazz == null) {
            clazz = urlServletMap.get(url);
        }
        try {
            Class<?> aClass = Class.forName(clazz);
            MyServlet myServlet = (MyServlet) aClass.newInstance();
            myServlet.service(myRequset, myResponse);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("没有对应方法");
        }
    }

    public static void main(String[] args) {
        new MyTomcat(9999).start();
    }

}
