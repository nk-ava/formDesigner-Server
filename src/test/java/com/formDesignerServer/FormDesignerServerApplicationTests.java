package com.formDesignerServer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.formDesignerServer.entity.*;
import com.formDesignerServer.mapper.SelectorMapper;
import com.formDesignerServer.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class FormDesignerServerApplicationTests {
    @Autowired
    private MyService myService;
    @Autowired
    private SelectorMapper selectorMapper;

    @Test
    void contextLoads() {
        List<Selector> list = selectorMapper.selectList(null);
        JSONArray.parseArray(JSONObject.toJSONString(list));
        //System.out.print(list.get(0).toString());
    }

}
