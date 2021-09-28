package com.cn.my.log.starter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.filter.OrderedFilter;

import javax.servlet.*;
import java.io.IOException;

import static com.cn.my.log.starter.LogUtils.LOG_ID;


/**
 * @author songshuai
 * @date 2020/1/10-19:24
 * @Description 过滤所有web请求，在其中加入日志通用部分
 */
@Slf4j
public class WebRequestLogFilter implements OrderedFilter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("{}过滤器初始化", this.getClass().getCanonicalName());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        MDC.put(LOG_ID, LogUtils.logId());
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            clean();
        } catch (Exception e) {
            clean();
            throw e;
        }

    }

    private void clean() {
        MDC.remove(LOG_ID);
    }

    @Override
    public void destroy() {
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
