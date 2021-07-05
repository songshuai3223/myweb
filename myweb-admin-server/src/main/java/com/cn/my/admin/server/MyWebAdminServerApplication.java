package com.cn.my.admin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyWebAdminServerApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MyWebAdminServerApplication.class);
        application.run(args);
    }
}
