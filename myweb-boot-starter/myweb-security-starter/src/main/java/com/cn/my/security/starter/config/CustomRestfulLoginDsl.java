package com.cn.my.security.starter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

/**
 * @author songshuai
 */
@Service
public class CustomRestfulLoginDsl extends AbstractHttpConfigurer<CustomRestfulLoginDsl, HttpSecurity> {

    @Autowired
    RestfulFilter restfulFilter;
    
    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Override
    public void init(HttpSecurity builder) throws Exception {
        builder.csrf().disable();
    }


    @Override
    public void configure(HttpSecurity builder) throws Exception {
        builder.addFilterBefore(restfulFilter, UsernamePasswordAuthenticationFilter.class);
        builder.addFilterBefore(validateCodeFilter, RestfulFilter.class);
    }
}
