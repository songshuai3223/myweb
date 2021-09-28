package com.cn.my.app.starter;

import com.cn.my.log.starter.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author songshuai
 * @date 2020/1/17-15:27
 * @Description 应用生命周期回调
 */
@Slf4j
public class MywebAppListenner implements SpringApplicationRunListener, Ordered {


    private final SpringApplication application;

    private final String[] args;


    public MywebAppListenner(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }


    @Override
    public void starting() {
        MDC.put(LogUtils.LOG_ID, LogUtils.logId());
        log.warn("======================= starting =====================================");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        log.warn("======================= environmentPrepared=====================================");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.warn("======================= contextPrepared =====================================");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.warn("======================= contextLoaded =====================================");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        log.warn("======================= started =====================================");

    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        log.warn("======================= running =====================================");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        log.error("======================= failed =====================================");
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
