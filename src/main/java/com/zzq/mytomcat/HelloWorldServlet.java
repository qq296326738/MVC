package com.zzq.mytomcat;

import java.io.IOException;

/**
 * @author zzz
 */
public class HelloWorldServlet extends MyServlet {
    @Override
    public void doGet(MyRequset myRequset, MyResponse myResponse) throws IOException {
        myResponse.write("get hello");
    }

    @Override
    public void doPost(MyRequset myRequset, MyResponse myResponse) throws IOException {
        myResponse.write("post hello");
    }
}
