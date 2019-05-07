package com.cloud.core.interceptor;

import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.io.IOException;

/**
 * @author: dayun_wang
 */
@Log4j
public class RequestInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes,
                                        ClientHttpRequestExecution execution) throws IOException {
        HttpRequestWrapper wrapper = new HttpRequestWrapper(httpRequest);
        String headers = StringUtils.collectionToDelimitedString(
                HandlerInterceptor.LABEL.get(),
                HandlerInterceptor.HEADER_LABEL_SPLIT);
        log.info("header={} ", headers);
        wrapper.getHeaders().add(HandlerInterceptor.HEADER_LABEL, headers);

        return execution.execute(wrapper, bytes);
    }
}
