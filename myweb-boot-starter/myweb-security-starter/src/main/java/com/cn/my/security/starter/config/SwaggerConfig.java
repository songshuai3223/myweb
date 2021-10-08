package com.cn.my.security.starter.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Operation;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 手动添加swagger接口
 *
 * @author caoxingrui
 * @date 2021/4/26-18:54
 * @Description
 */
@Component
@EnableSwagger2
public class SwaggerConfig implements ApiListingScannerPlugin {
    /***
     *验证报文
     * {
        "imageCode": "1234",
        "password": "SrLLMry4zk9VnM3Ex2ChIicW2wRs29f0xuqeUzVqUOAWom/L0aj9tt85OL4o7v0Lw97obptR0PlAzImElU9bTmCd99jHWwSOAj32ZmYmVnUGYfNXpdo8GqFVdcSoQS9/LDS8HruWfO17bT+CK09+8sy9PVt8FJw41PZzQSPkdRLptipBdIzEhOScpln0baOzOwZt4FmNZ4CEDWvAF8DuPSAiiEjAFJKOLSWu24e9y1q7hIMwtsD65eNs0FPuPy+8mQ8IjDzTnVKZwJcwFnHFzlGI0l/Uws3FWBF728QCMU1gHWaPNmI5bZcNtQRnepmfiIS0f3FAhFL3rc9s92+soQ==",
        "username": "13712345678"
     * }
     */

    @Autowired
    private TypeResolver typeResolver;

    @Override
    public List<ApiDescription> apply(DocumentationContext context) {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .build();
        docket.securitySchemes(Lists.newArrayList(new ApiKey("Set-Cookie", "Set-Cookie", "header")));

        Operation usernamePasswordOperation = new OperationBuilder(new CachingOperationNameGenerator())
                .method(HttpMethod.POST)
                .summary("用户名密码登录")
                .consumes(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE)) // 接收参数格式
                .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE)) // 返回参数格式
                .tags(Sets.newHashSet("登录"))
                .parameters(Arrays.asList(
                        new ParameterBuilder()
                                .description("req")
                                .type(typeResolver.resolve(DspSwaggerUser.class))
                                .name("req")
                                .defaultValue("{\"password\": \"123456a\",\"username\": \"13712345678\" }")
                                .parameterType("body")
                                .parameterAccess("access")
                                .modelRef(new ModelRef("DspSwaggerUser"))
                                .build()
                ))
                .responseMessages(Collections.singleton(
                        new ResponseMessageBuilder().code(200).message("ok")
                                .responseModel(new ModelRef("RestResponse")).build()))
                .build();

        ApiDescription loginApiDescription = new ApiDescription("login", "/login", "登录接口",
                Arrays.asList(usernamePasswordOperation), false);

        return Arrays.asList(loginApiDescription);
    }

    /**
     * 是否使用此插件
     *
     * @param documentationType swagger文档类型
     * @return true 启用
     */
    @Override
    public boolean supports(DocumentationType documentationType) {
        return DocumentationType.SWAGGER_2.equals(documentationType);
    }

    @Data
    @ApiModel(value = "DspSwaggerUser", description = "自定义的登录用户")
    public static class DspSwaggerUser {
        /**
         * 用户名
         */
        @ApiModelProperty(allowableValues = "13712345678")
        private String username;

        /**
         * 密码
         */
        @ApiModelProperty(allowableValues = "SrLLMry4zk9VnM3Ex2ChIicW2wRs29f0xuqeUzVqUOAWom/L0aj9tt85OL4o7v0Lw97obptR0PlAzImElU9bTmCd99jHWwSOAj32ZmYmVnUGYfNXpdo8GqFVdcSoQS9/LDS8HruWfO17bT+CK09+8sy9PVt8FJw41PZzQSPkdRLptipBdIzEhOScpln0baOzOwZt4FmNZ4CEDWvAF8DuPSAiiEjAFJKOLSWu24e9y1q7hIMwtsD65eNs0FPuPy+8mQ8IjDzTnVKZwJcwFnHFzlGI0l/Uws3FWBF728QCMU1gHWaPNmI5bZcNtQRnepmfiIS0f3FAhFL3rc9s92+soQ==")
        private String password;

        /**
         * 密码
         */
        @ApiModelProperty(allowableValues = "1234")
        private String imageCode;
    }
}
