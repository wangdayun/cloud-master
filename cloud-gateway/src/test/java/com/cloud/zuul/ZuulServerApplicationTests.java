package com.cloud.zuul;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZuulServerApplicationTests {

	@Test
	public void contextLoads() {
		//获取所有的属性
		Properties properties = System.getProperties();
		//为了看看系统的属性有几个，加了一个计数器
		int count = 0;
		//遍历所有的属性
		for (String key : properties.stringPropertyNames()) {
			System.out.println(key + "=" + properties.getProperty(key));
			count++;
			if (key.equalsIgnoreCase("SpringCloud.Authorization")){
				System.out.println("YES");
				return ;
			}

		}
		System.out.println(count);

	}
}
