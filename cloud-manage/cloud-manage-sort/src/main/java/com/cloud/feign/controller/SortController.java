package com.cloud.feign.controller;

import com.cloud.feign.service.SortService;
import com.cloud.feign.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 分类
 *
 * @author dayun_wang
 */
@RestController
public class SortController {

    @Autowired
    private SortService sortService;

    @RequestMapping(value = "getSortMessage")
    @ResponseBody
    public List getSortMessage() {
        return sortService.getSortMessage();
    }

    @RequestMapping(value = "getTestMessage")
    @ResponseBody
    public DataUtils getTestMessage(HttpServletRequest request) throws IOException {
        return DataUtils.ok(sortService.getTestMessage());
    }
}
