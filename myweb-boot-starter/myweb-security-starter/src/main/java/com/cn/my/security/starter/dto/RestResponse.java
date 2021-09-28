package com.cn.my.security.starter.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 所有response的
 * rest返回体基类,用于控制返回报文格式通用部分
 *
 * @author i_brwang
 * @version V1.0
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "公共响应信息")
public class RestResponse<T> implements Serializable {

    @ApiModelProperty(value = "http返回状态码，成功为200", required = true, allowableValues = "参考Http状态码")
    private int errcode;
    @ApiModelProperty(value = "状态码原因描述")
    private String errmsg;
    @ApiModelProperty(value = "响应唯一标识符")
    private String responseId = UUID.randomUUID().toString();

    @ApiModelProperty(value = "响应时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSS")
    private LocalDateTime responseTime = LocalDateTime.now();
    @ApiModelProperty(value = "返回数据")
    private T data;

    public static final Object NONE = null;

    private RestResponse(HttpStatus status, T data) {
        this.errcode = status.value();
        this.errmsg = status.getReasonPhrase();
        this.data = data;
    }

    private RestResponse(HttpStatus status, T data, String description) {
        this.errcode = status.value();
        if (!StringUtils.isEmpty(description)) {
            this.errmsg = description;
        } else {
            this.errmsg = status.getReasonPhrase();
        }
        this.data = data;
    }

    public static <T> RestResponse<T> response(HttpStatus status, T data) {
        return new RestResponse<>(status, data);
    }

    public static RestResponse response(HttpStatus status, String description) {
        return new RestResponse<>(status, null, description);
    }

    public static <T> RestResponse<T> successGet(T data) {
        return new RestResponse<>(HttpStatus.OK, data);
    }


    public static <T> RestResponse<T> successPost(T data) {
        return new RestResponse<>(HttpStatus.CREATED, data);
    }

    public static <T> RestResponse<T> errorPost(T data) {
        return new RestResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, data);
    }

    public static <T> RestResponse<T> successPut() {
        return new RestResponse<>(HttpStatus.CREATED, (T) NONE);
    }

    @SuppressWarnings("unchecked")
    public static <T> RestResponse<T> successPatch() {
        return new RestResponse<>(HttpStatus.CREATED, (T) NONE);
    }


    public static <T> RestResponse<T> successSubmit(T data) {
        return new RestResponse<>(HttpStatus.ACCEPTED, data);
    }

    @SuppressWarnings("unchecked")
    public static <T> RestResponse<T> notFound() {
        return new RestResponse<>(HttpStatus.NOT_FOUND, (T) NONE);
    }

}