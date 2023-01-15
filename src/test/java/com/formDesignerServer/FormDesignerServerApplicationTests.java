package com.formDesignerServer;

import com.formDesignerServer.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class FormDesignerServerApplicationTests {
    @Autowired
    private MyService myService;

    @Test
    void contextLoads() {
        System.out.println((new Date()).getTime());
    }

}
