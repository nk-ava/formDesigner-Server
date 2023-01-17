package com.formDesignerServer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.formDesignerServer.entity.*;
import com.formDesignerServer.mapper.FormMapper;
import com.formDesignerServer.mapper.MyMapper;
import com.formDesignerServer.mapper.SelectorMapper;
import com.formDesignerServer.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@SpringBootTest
class FormDesignerServerApplicationTests {
    @Autowired
    private MyService myService;
    @Autowired
    private SelectorMapper selectorMapper;
    @Autowired
    private MyMapper myMapper;
    @Autowired
    private FormMapper formMapper;

    @Test
    void contextLoads() {
        Map<String,Object> mp = myMapper.getFormById("1673873067752").get(0);
        System.out.println(mp.get("template_id"));
        System.out.println(mp.get("id"));
        System.out.println(mp.get("submit_time"));
        System.out.println(selectorMapper.selectList(null).get(0).toString());
        System.out.println(formMapper.selectList(null).get(0).toString());
    }

}
