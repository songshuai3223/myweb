package com.cn.my.security.starter.config;

import com.cn.my.security.starter.dto.RestResponse;
import com.cn.my.security.starter.service.impl.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description TODO
 * @Date 2021/4/21 14:51
 * @Created by songshuai
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableJdbcHttpSession
@EnableGlobalMethodSecurity(prePostEnabled = true)  //  启用方法级别的权限认证
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CustomRestfulLoginDsl customRestfulLoginDsl;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  允许所有用户访问"/"和"/index.html"
        http.userDetailsService(userDetailsService).authorizeRequests()
                .antMatchers("/login/**", "/", "/index.html", "/v2/api-docs", "/swagger-resources/configuration/ui",
                        "/swagger-resources", "/swagger-resources/configuration/security",
                        "/swagger-ui.html", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and().apply(customRestfulLoginDsl)
                .and().formLogin().successForwardUrl("/login/success")
                .failureHandler((request, response, exception) -> sendFailureResponse(response, "账户或密码错误！"))
                .and().exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> sendFailureResponse(response, "认证失败！"))
                .and()
                .logout()
                .logoutSuccessUrl("/login/logout/success")
                .invalidateHttpSession(true);

    }

    private void sendFailureResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        RestResponse restResponse = RestResponse.response(HttpStatus.UNAUTHORIZED, message);
        response.getOutputStream().write(objectMapper.writeValueAsBytes(restResponse));
        response.getOutputStream().flush();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
