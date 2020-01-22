package com.lubn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 想要咨询vip课程相关的同学加一下安其拉老师QQ：3164703201
 * 想要往期视频的同学加一下木兰老师QQ：2746251334
 * author：鲁班学院-商鞅老师
 */
@RestController
public class PowerController {


    @RequestMapping("/getPower.do")
    public Object getPoser(String name) throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("key","power1");
//        if (name==null)
//            throw new Exception();
        return map;
    }

    @RequestMapping("/getPowerLists.do")
    public Object getPoserList(String name) throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("key","powerLists1");
        return map;
    }
}
