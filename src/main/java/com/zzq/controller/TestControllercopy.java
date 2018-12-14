//package com.liugh.controller;
//
//import com.zzq.annotation.MyController;
//import com.zzq.annotation.MyRequestMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@MyController
//@MyRequestMapping("/test")
//public class TestControllercopy {
//
//    @MyRequestMapping("/doTest")
//    public void test1(HttpServletRequest request, HttpServletResponse response/*,
//                      @MyRequestParam("param") String param*/) {
////        System.out.println(param);
//        try {
//            response.getWriter().write("doTest method success! param:" /*+ param*/);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @MyRequestMapping("/doTest2")
//    public void test2(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            response.getWriter().println("doTest2 method success!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @MyRequestMapping("/doTest3")
//    public void test3(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            request.getRequestDispatcher("/11.html").forward(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}
