package com.cn.my.security.starter.config;

import com.cn.my.common.starter.util.JsonUtil;
import com.cn.my.security.starter.dto.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author songshuai
 */
@Slf4j
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails user = null;
        if (Objects.nonNull(principal) && principal instanceof UserDetails ) {
            user  = (UserDetails) principal;
        }
        if (user == null) {
            return false;
        }
        Set<String> roleCode = user.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        String url = request.getRequestURI();
        log.info("the role is {} {} ,visiting path {}", user.getUsername(), roleCode, url);
        if (!checkAuthority(roleCode, url)) {
            log.error("the role is {} {} ,visiting path {} without enough authority", user.getUsername(), roleCode, url);
            visitError(response);
            return false;
        }
        return true;
    }

    private boolean checkAuthority(Set<String> roleCode, String url) {
        return true;
    }

    private void visitError(HttpServletResponse response) throws Exception {
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getOutputStream().write(JsonUtil.getObjectMapper().writeValueAsBytes(RestResponse.response(HttpStatus.UNAUTHORIZED, "没有该接口的访问权限！")));
        response.getOutputStream().flush();
    }


}
