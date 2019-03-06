package com.qmx.member.enumerate;

public enum MemberSex {
    male("男"),
    female("女"),
    unknown("未知");

    private String title;

    MemberSex(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
