package com.zzq.mytomcat;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zzz
 * 请求对象
 */
public class MyRequset {
    private String url;
    private String method;

    public MyRequset(InputStream inputStream) throws IOException {
        String httpRequest = "";
        byte[] httpRequestBytes = new byte[1024];
        int length = 0;
        if ((length = inputStream.read(httpRequestBytes)) > 0) {
            httpRequest = new String(httpRequestBytes, 0, length);
        }
        /**
                                                HTTP请求
         GET /girl HTTP/1.1
         Host: 192.168.3.6:9999
         Connection: keep-alive
         Cache-Control: max-age=0
         Upgrade-Insecure-Requests: 1
         User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36
         Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng;q=0.8
         Accept-Encoding: gzip, deflate
         Accept-Language: zh-CN,zh;q=0.9
         */

        String httpHead = httpRequest.split("\n")[0];
        if (httpHead.equals("GET /favicon.ico HTTP/1.1\r")) {
            return;
        }
        url = httpHead.split("\\s")[1];
        method = httpHead.split("\\s")[0];
        System.out.println(httpRequest);
//        System.out.println(this);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
