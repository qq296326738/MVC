package com.zzq.controller;

import com.zzq.annotation.MyController;
import com.zzq.annotation.MyRequestMapping;
import com.zzq.mytomcat.MyRequset;
import com.zzq.mytomcat.MyResponse;

import java.io.IOException;

@MyController
@MyRequestMapping("/test")
public class TestController {

    @MyRequestMapping("/doTest")
    public void test1(MyRequset request, MyResponse response/*,
                      @MyRequestParam("param") String param*/) {
        try {
            response.write("doTest method success! param:" /*+ param*/);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @MyRequestMapping("/doTest2")
    public void test2(MyRequset request, MyResponse response) {
        try {
            response.write("doTest2 method success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping("/doTest3")
    public void test3(MyRequset request, MyResponse response) {
        try {
//            request.getRequestDispatcher("/11.html").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
