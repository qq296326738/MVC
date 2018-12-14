package com.zzq.mytomcat;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author zzz
 * 响应对象
 */
public class MyResponse {
    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream) throws IOException {
        this.outputStream = outputStream;
    }

    public void write(String contend) throws IOException {
        StringBuffer httpResponse = new StringBuffer();
        httpResponse.append("HTTP/1.1 200 ok\n")
                .append("Contend-Type: Text/html\n")
                .append("\r\n")
                .append("<html><body>")
                .append(contend)
                .append("</body></html>");
        outputStream.write(httpResponse.toString().getBytes());
        outputStream.close();
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
