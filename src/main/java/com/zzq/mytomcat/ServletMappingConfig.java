package com.zzq.mytomcat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzz
 */
public class ServletMappingConfig {

    public static List<ServletMapping> servletMappingList = new ArrayList<>();

    static {
//        servletMappingList.add(new ServletMapping("findGirl","/girl","com.zzq.mytomcat.FindGirlServlet"));
//        servletMappingList.add(new ServletMapping("helloWorld","/helloWorld","com.zzq.mytomcat.HelloWorldServlet"));
        //结合手写的mvc
        servletMappingList.add(new ServletMapping("DispatcherServlet", "/", "com.zzq.servlet.ZzqDispatcherServlet"));
    }

}
