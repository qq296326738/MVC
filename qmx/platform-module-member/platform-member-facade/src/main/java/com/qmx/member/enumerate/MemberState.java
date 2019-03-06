package com.qmx.member.enumerate;

/**
 * 会员状态
 */
public enum MemberState {
    normal("正常"),
    locking("锁定"),
    lose("挂失"),
    overdue("过期");


    private String title;

    MemberState(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
