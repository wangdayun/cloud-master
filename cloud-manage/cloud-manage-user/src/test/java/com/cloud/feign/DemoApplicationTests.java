package com.cloud.feign;

import com.cloud.feign.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getSortMessage(){
        List list = userService.getSortMessage();
        System.out.println("list长度："+list.size());
    }
}
