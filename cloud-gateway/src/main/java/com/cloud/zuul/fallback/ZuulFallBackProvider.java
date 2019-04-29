package com.cloud.zuul.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * 路由回退机制
 *
 * @author: dayun_wang
 */
@Component
public class ZuulFallBackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof HystrixTimeoutException){
            return response(HttpStatus.GATEWAY_TIMEOUT);
        }else {
            return fallbackResponse();
        }
    }


    public ClientHttpResponse fallbackResponse() {
        return response(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ClientHttpResponse response(HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode(){
                return status;
            }

            @Override
            public int getRawStatusCode(){
                return status.value();
            }

            @Override
            public String getStatusText(){
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
                System.out.println("服务器关闭...");
            }

            @Override
            public InputStream getBody(){
                String message = "{\n" +
                                    "\"status\": false,\n" +
                                    "\"message\": \"服务器发生故障, 请稍后再试...\"\n" +
                                 "}";
                return new ByteArrayInputStream(message.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
