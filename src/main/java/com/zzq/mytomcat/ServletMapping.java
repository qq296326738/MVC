package com.zzq.mytomcat;

public class ServletMapping {

    private String servletName;
    private String url;
    private String calzz;

    public ServletMapping(String servletName, String url, String calzz) {
        this.servletName = servletName;
        this.url = url;
        this.calzz = calzz;
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCalzz() {
        return calzz;
    }

    public void setCalzz(String calzz) {
        this.calzz = calzz;
    }
}
