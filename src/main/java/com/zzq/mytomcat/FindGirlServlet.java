package com.zzq.mytomcat;

import java.io.IOException;

/**
 * @author zzz
 */
public class FindGirlServlet extends MyServlet {
    @Override
    public void doGet(MyRequset myRequset, MyResponse myResponse) throws IOException {
        myResponse.write("get Girl");
    }

    @Override
    public void doPost(MyRequset myRequset, MyResponse myResponse) throws IOException {
        myResponse.write("post Girl");
    }
}
