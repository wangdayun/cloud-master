package com.cloud.feign.controller;

import com.alibaba.fastjson.JSON;
import com.cloud.feign.service.UserService;
import com.cloud.feign.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 用户信息
 *
 * @author dayun_wang
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "getUserMessage")
    @ResponseBody
    public DataUtils getUserMessage() throws IOException {
        return DataUtils.ok(userService.getUserMessage());
    }

    @RequestMapping(value = "getSortMessage")
    public DataUtils getSortMessage(HttpServletRequest request) throws IOException {
        System.out.println("authorization:"+request.getHeader("access-token"));
        return DataUtils.ok(userService.getSortMessage());
    }
}
