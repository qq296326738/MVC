package com.zzq.common.TestProxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Proxy {
    public static Object newProxyInstance(Class infce, InvocationHandler invocationHandler) throws Exception { //JDK6 Complier API, CGLib, ASM
        String methodStr = "";
        String rt = "\r\n";

        Method[] methods = infce.getMethods();

        for (Method m : methods) {
            methodStr += "@Override" + rt +
                    "public void " + m.getName() + "() {" + rt +
                    "    try {" + rt +
                    "    Method md = " + infce.getName() + ".class.getMethod(\"" + m.getName() + "\");" + rt +
                    "    h.invoke(this, md);" + rt +
                    "    }catch(Exception e) {e.printStackTrace();}" + rt +
                    "       catch (Throwable throwable) {\n" +
                    "            throwable.printStackTrace();\n" +
                    "        }\n" + rt +

                    "}";
        }

        String src =
                "package com.zzq.common;" + rt +
                        "import java.lang.reflect.Method;" + rt +
                        "public class $Proxy1 implements " + infce.getName() + "{" + rt +
                        "    public $Proxy1(InvocationHandler h) {" + rt +
                        "        this.h = h;" + rt +
                        "    }" + rt +


                        "    com.zzq.common.TestProxy.InvocationHandler h;" + rt +

                        methodStr +
                        "}";
        String fileName =
                "D:\\test\\mvc\\src\\main\\java\\com\\zzq\\common\\$Proxy1.java";
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(src);
        fw.flush();
        fw.close();

        //compile
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
        Iterable units = fileMgr.getJavaFileObjects(fileName);
        JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
        t.call();
        fileMgr.close();

        //load into memory and create an instance
        URL[] urls = new URL[]{new URL("file:/" + "D:\\test\\mvc\\src\\main\\java")};
        URLClassLoader ul = new URLClassLoader(urls);
        Class c = ul.loadClass("com.zzq.common.$Proxy1");
        System.out.println(c);

        Constructor ctr = c.getConstructor(InvocationHandler.class);
        Object m = ctr.newInstance(invocationHandler);
        return m;
    }
}
