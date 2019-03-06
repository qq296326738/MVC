package com.qmx.member.enumerate;

public enum IntegralType {

    recharge("充值"),
    consumption("消费"),
    register("注册");

    private String title;

    IntegralType(String s) {
        this.title = s;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
