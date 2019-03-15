package com.timebusker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @DESC:SpringBootApplication:spring-boot启动类
 * @author:timebusker
 * @date:2019/3/15
 */
@SpringBootApplication
@MapperScan("com.timebusker.mapper")
public class ApplicationStarter extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStarter.class);

    /**
     * 启动服务
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ApplicationStarter.class);
    }
}
