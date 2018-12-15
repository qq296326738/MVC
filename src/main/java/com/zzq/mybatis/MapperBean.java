package com.zzq.mybatis;

import java.util.List;

public class MapperBean {
    private String interfaceName;
    private List<Function> list;

    public List<Function> getList() {
        return list;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public void setList(List<Function> list) {
        this.list = list;
    }
}

