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

import java.util.*;
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

    private Pattern pattern = Pattern.compile("^\\[(.)*\\]$");
    @Test
    void contextLoads() {
        String num = "6";
        JSON.parseObject(num);
        System.out.println(JSON.parseObject(num));
    }

}
