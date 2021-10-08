package com.cn.my.security.starter.config;

import com.cn.my.common.starter.util.ApiAssert;
import com.cn.my.common.starter.util.JsonUtil;
import com.cn.my.security.starter.controller.LoginController;
import com.cn.my.security.starter.dto.LoginRequest;
import com.cn.my.security.starter.dto.RestResponse;
import com.cn.my.security.starter.module.ImageCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author songshuai
 * @date 2021/5/19-13:58
 * @Description
 */
@Slf4j
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${dsp.code.validate:true}")
    private boolean validate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if ("/login".equals(request.getRequestURI())) {
            log.info("restful validate start");

            if (!validate) {
                filterChain.doFilter(request, response);
                return;
            }

            CustomCacheWrapper requestWrapper = new CustomCacheWrapper(request);
            try {
                String aa = requestWrapper.getBody();
                LoginRequest body = JsonUtil.getObjectMapper().readValue(aa, LoginRequest.class);
                // 验证码验证
                ImageCode codeInSession = (ImageCode) request.getSession().getAttribute(LoginController.SESSION_KEY);
//                String codeInRequest = body.getImageCode();
//                if (!StringUtils.hasText(codeInRequest) || codeInSession == null) {
//                    ApiAssert.failure(HttpStatus.BAD_REQUEST, "验证码不能为空!");
//                } else if (codeInSession.isExpried()) {
//                    request.getSession().removeAttribute(LoginController.SESSION_KEY);
//                    ApiAssert.failure(HttpStatus.BAD_REQUEST, "验证码已过期!");
//                } else if (!codeInSession.getCode().equals(codeInRequest)) {
//                    ApiAssert.failure(HttpStatus.BAD_REQUEST, "验证码错误!");
//                }
                request.getSession().removeAttribute(LoginController.SESSION_KEY);
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
        RestResponse restResponse = RestResponse.response(HttpStatus.INTERNAL_SERVER_ERROR, "验证码错误");
        response.getOutputStream().write(objectMapper.writeValueAsBytes(restResponse));
        response.getOutputStream().flush();
    }
}
