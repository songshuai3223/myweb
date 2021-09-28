package com.cn.my.common.starter.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author songshuai
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel("服务测试响应")
@NoArgsConstructor
public class ApiResponse {

    @ApiModelProperty(value = "响应码", example = "200")
    private int statusCode;

    @ApiModelProperty(value = "响应体", example = "{}")
    private JsonNode bodyMsg;

}
