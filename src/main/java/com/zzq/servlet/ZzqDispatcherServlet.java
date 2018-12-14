package com.zzq.servlet;

import com.zzq.annotation.MyController;
import com.zzq.annotation.MyRequestMapping;
import com.zzq.mytomcat.MyRequset;
import com.zzq.mytomcat.MyResponse;
import com.zzq.mytomcat.MyServlet;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @author zzq
 */
public class ZzqDispatcherServlet extends MyServlet {

    /**
     * 读取配置文件
     */
    private Properties properties = new Properties();
    /**
     * 扫包获取类名称集合
     */
    private List<String> classNames = new ArrayList<>();
    /**
     * 根据类名称集合,默认已类名开头小写为KEY,VALUE=类名称反射实例
     */
    private Map<String, Object> ioc = new HashMap<>();
    /**
     * 遍历ioc容器的类,读取注解上的url路径,把url为key,Method为value放入集合
     */
    private Map<String, Method> handlerMapping = new HashMap<>();
    /**
     * 读取注解上的url路径,把url为key,controller为value放入集合
     */
    private Map<String, Object> controllerMap = new HashMap<>();

    public void init(String pz) throws ServletException {

        //2.初始化所有相关联的类,扫描用户设定的包下面所有的类
        doScanner(pz);

        //3.拿到扫描到的类,通过反射机制,实例化,并且放到ioc容器中(k-v  beanName-bean) beanName默认是首字母小写
        doInstance();

        //4.初始化HandlerMapping(将url和method对应上)
        initHandlerMapping();


    }

    @Override
    public void doGet(MyRequset req, MyResponse resp) throws Exception {
        this.init("com.zzq.controller");
        this.doPost(req, resp);
    }

    @Override
    public void doPost(MyRequset req, MyResponse resp) throws IOException {
        try {
            //处理请求
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.write("500!! Server Exception");
        }
    }

    private void doDispatch(MyRequset req, MyResponse resp) throws Exception {
        if (handlerMapping.isEmpty()) {
            return;
        }

        String url = req.getUrl();

        if (!this.handlerMapping.containsKey(url)) {
            resp.write("404 NOT FOUND!");
            return;
        }

        Method method = this.handlerMapping.get(url);


        //利用反射机制来调用
        try {
            //第一个参数是method所对应的实例 在ioc容器中
            method.invoke(this.controllerMap.get(url), new Object[]{req,resp});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载配置文件
     *
     * @param location
     */
    private void doLoadConfig(String location) {
        //把web.xml中的contextConfigLocation对应value值的文件加载到流里面
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(location);
        try {
            //用Properties文件加载文件里的内容
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关流
            if (null != resourceAsStream) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 扫包,包类名放入集合
     *
     * @param packageName
     */
    private void doScanner(String packageName) {
        //把所有的.替换成/
        String name =  packageName.replaceAll("\\.", "/");
        URL url = this.getClass().getClassLoader().getResource(name);
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                //递归读取包
                doScanner(packageName + "." + file.getName());
            } else {
                String className = packageName + "." + file.getName().replace(".class", "");
                classNames.add(className);
            }
        }
    }

    /**
     * 将有Controller注解的类放入容器
     */
    private void doInstance() {
        if (classNames.isEmpty()) {
            return;
        }
        for (String className : classNames) {
            try {
                //把类搞出来,反射来实例化(只有加@MyController需要实例化)
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(MyController.class)) {
                    ioc.put(toLowerFirstWord(clazz.getSimpleName()), clazz.newInstance());
                } else {
                    continue;
                }


            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 将url为key,放入handlerMapping和ControllerMap中
     */
    private void initHandlerMapping() {
        if (ioc.isEmpty()) {
            return;
        }
        try {
            for (Map.Entry<String, Object> entry : ioc.entrySet()) {
                Class<? extends Object> clazz = entry.getValue().getClass();
                if (!clazz.isAnnotationPresent(MyController.class)) {
                    continue;
                }

                //拼url时,是controller头的url拼上方法上的url
                String baseUrl = "";
                if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                    MyRequestMapping annotation = clazz.getAnnotation(MyRequestMapping.class);
                    baseUrl = annotation.value();
                }
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (!method.isAnnotationPresent(MyRequestMapping.class)) {
                        continue;
                    }
                    MyRequestMapping annotation = method.getAnnotation(MyRequestMapping.class);
                    String url = annotation.value();

                    url = (baseUrl + "/" + url).replaceAll("/+", "/");
                    handlerMapping.put(url, method);
                    controllerMap.put(url, clazz.newInstance());
                    System.out.println(url + "," + method);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 把字符串的首字母小写
     *
     * @param name
     * @return
     */
    private String toLowerFirstWord(String name) {
        char[] charArray = name.toCharArray();
        charArray[0] += 32;
        return String.valueOf(charArray);
    }

}
