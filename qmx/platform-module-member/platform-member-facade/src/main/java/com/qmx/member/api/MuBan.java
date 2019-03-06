package com.qmx.member.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/MuBan")
public interface MuBan {
    @RequestMapping(value = "/路径", method = RequestMethod.POST)
    public String findList(@RequestParam("name") String name,
                           @RequestBody String json变对象);

}
