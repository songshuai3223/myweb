package com.cn.my.security.starter.config;

import com.cn.my.common.starter.util.JsonUtil;
import com.cn.my.common.starter.util.RSAEncryptUtil;
import com.cn.my.security.starter.dto.LoginRequest;
import com.cn.my.security.starter.dto.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author songshuai
 */
@Slf4j
@Component
public class RestfulFilter extends OncePerRequestFilter {

    @Autowired
    ObjectMapper objectMapper;
    @Value("${encodec.private-key}")
    private String privateKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if ("/login".equals(request.getRequestURI())) {
            CustomCacheWrapper requestWrapper = null;
            if (request instanceof CustomCacheWrapper) {
                requestWrapper = (CustomCacheWrapper) request;
            } else {
                requestWrapper = new CustomCacheWrapper(request);
            }
            log.info("restful login start");
            try {
                String aa = requestWrapper.getBody();
                LoginRequest body = JsonUtil.getObjectMapper().readValue(aa, LoginRequest.class);
                log.info("login body is {}", body);
                String decodedString = RSAEncryptUtil.decrypt(body.getPassword(), privateKey);
                requestWrapper.setParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, body.getUsername());
                requestWrapper.setParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY, decodedString);
            } catch (Exception e) {
                log.error(e.getLocalizedMessage(), e);
                sendFailureResponse(response);
                return;
            }
            filterChain.doFilter(requestWrapper, response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private void sendFailureResponse(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        RestResponse restResponse = RestResponse.response(HttpStatus.INTERNAL_SERVER_ERROR, "登录出现异常");
        response.getOutputStream().write(objectMapper.writeValueAsBytes(restResponse));
        response.getOutputStream().flush();
    }
}
