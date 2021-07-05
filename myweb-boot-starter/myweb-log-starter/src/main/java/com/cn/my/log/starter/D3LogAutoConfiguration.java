package com.cn.my.log.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author Bryan Wang
 * @date 2020/1/10-19:54
 * @Description
 */
@Configuration
public class D3LogAutoConfiguration {
    @ConditionalOnWebApplication
    @Bean
    public FilterRegistrationBean<WebRequestLogFilter> webRequestLogFilter() {
        FilterRegistrationBean<WebRequestLogFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new WebRequestLogFilter());
        registration.setName(WebRequestLogFilter.class.getName());
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registration.addUrlPatterns("/*");
        return registration;
    }
}
