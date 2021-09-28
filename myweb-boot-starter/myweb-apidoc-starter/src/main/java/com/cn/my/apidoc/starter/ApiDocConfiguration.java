package com.cn.my.apidoc.starter;

import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author songshuai
 * @date 2020/1/10-21:51
 * @Description
 */
@ConfigurationProperties(prefix = "api.doc")
public class ApiDocConfiguration {
    private String version;
    private String title = "数字广东公司-数据平台部-数据研发中心";
    private String description = "数据平台部API文档";
    private boolean useDefaultResponseMessages = false;
    /**
     * 包逻辑
     */
    private List<String> packages = Lists.newArrayList();

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isUseDefaultResponseMessages() {
        return useDefaultResponseMessages;
    }

    public void setUseDefaultResponseMessages(boolean useDefaultResponseMessages) {
        this.useDefaultResponseMessages = useDefaultResponseMessages;
    }

    public List<String> getPackages() {
        return packages;
    }

    public void setPackages(List<String> packages) {
        this.packages = packages;
    }
}
