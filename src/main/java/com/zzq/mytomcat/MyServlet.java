package com.zzq.mytomcat;

import java.io.IOException;

/**
 * @author zzz
 */
public abstract class MyServlet {

    private final String POST = "POST";
    private final String GET = "GET";

    public abstract void doGet(MyRequset myRequset, MyResponse myResponse) throws IOException, Exception;

    public abstract void doPost(MyRequset myRequset, MyResponse myResponse) throws IOException;

    public void service(MyRequset myRequset, MyResponse myResponse) throws Exception {
        if (POST.equalsIgnoreCase(myRequset.getMethod())) {
            doPost(myRequset, myResponse);
        } else if (GET.equalsIgnoreCase(myRequset.getMethod())) {
            doGet(myRequset, myResponse);
        }
    }

}
