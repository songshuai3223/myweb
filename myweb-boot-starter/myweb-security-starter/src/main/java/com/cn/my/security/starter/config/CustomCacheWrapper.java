package com.cn.my.security.starter.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author songshuai
 */
@Slf4j
public class CustomCacheWrapper extends HttpServletRequestWrapper {
    private static final String FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private Map<String, String[]> params = new HashMap<>();

    private String body;


    @Nullable
    private ServletInputStream inputStream;

    @Nullable
    private BufferedReader reader;

    public CustomCacheWrapper(HttpServletRequest request) throws IOException {
        super(request);
        StringBuilder stringBuilder = new StringBuilder();
        if (HttpMethod.POST.matches(getMethod())) {
            try {
                Thread.sleep(100);
                //若不加入，则可能导致登录请求体无法读取。。。
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try (BufferedReader bufferedReader = request.getReader()) {
                if (bufferedReader != null) {
                    int b = -1;
                    char[] charBuffer = new char[128];
                    while ((b = bufferedReader.read(charBuffer)) > 0) {
                        stringBuilder.append(charBuffer, 0, b);
                    }
                }
            }
            log.info("finish writing");
            body = stringBuilder.toString();
        }
    }

    public String getBody() {
        return body;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (!HttpMethod.POST.matches(getMethod())) {
            return super.getInputStream();
        }
        if (this.inputStream == null) {
            this.inputStream = new CustomCacheInputStream(getRequest().getInputStream());
        }
        return this.inputStream;
    }


    @Override
    public String getCharacterEncoding() {
        String enc = super.getCharacterEncoding();
        return (enc != null ? enc : WebUtils.DEFAULT_CHARACTER_ENCODING);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        if (this.reader == null) {
            this.reader = new BufferedReader(new InputStreamReader(getInputStream(), getCharacterEncoding()));
        }
        return this.reader;
    }

    @Override
    public String getParameter(String name) {
        if (CollectionUtils.isEmpty(params) && isFormPost()) {
            params.putAll(getRequest().getParameterMap());
        }
        if (params.get(name) == null || params.get(name).length == 0) {
            return null;
        }
        return params.get(name)[0];
    }

    public void setParameter(String name, String value) {
        params.put(name, new String[]{value});
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        if (CollectionUtils.isEmpty(params) && isFormPost()) {
            params.putAll(getRequest().getParameterMap());
        }
        return params;
    }


    @Override
    public String[] getParameterValues(String name) {
        if (CollectionUtils.isEmpty(params) && isFormPost()) {
            params.putAll(getRequest().getParameterMap());
        }
        return params.get(name);
    }


    private boolean isFormPost() {
        String contentType = getContentType();
        return (contentType != null && contentType.contains(FORM_CONTENT_TYPE) &&
                HttpMethod.POST.matches(getMethod()));
    }


    private class CustomCacheInputStream extends ServletInputStream {

        private ServletInputStream is;
        private ByteArrayInputStream byteArrayInputStream;

        public CustomCacheInputStream(ServletInputStream is) throws IOException {
            this.is = is;
            byteArrayInputStream = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
        }

        @Override
        public int read() {
            return byteArrayInputStream.read();
        }

        @Override
        public int read(byte[] b) throws IOException {
            return byteArrayInputStream.read(b);
        }

        @Override
        public int read(final byte[] b, final int off, final int len) throws IOException {
            return byteArrayInputStream.read(b, off, len);
        }


        @Override
        public boolean isFinished() {
            return this.is.isFinished();
        }

        @Override
        public boolean isReady() {
            return this.is.isReady();
        }

        @Override
        public void setReadListener(ReadListener readListener) {
            this.is.setReadListener(readListener);
        }
    }


}
