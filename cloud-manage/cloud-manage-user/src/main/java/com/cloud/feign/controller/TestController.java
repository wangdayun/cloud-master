package com.cloud.feign.controller;

import com.cloud.feign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 测试接口
 *
 * @author dayun_wang
 */
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "getTestMessage")
    public List getTestMessage(HttpServletRequest request){
        return userService.getUserMessage();
    }
}
