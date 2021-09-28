package com.cn.my.security.starter.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author songshuai
 * @date 2021/5/19-10:49
 * @Description
 */
@Data
@ApiModel(description = "验证码图片")
public class ImageCode implements Serializable {

    /**
     * 图片流
     */
    @ApiModelProperty("图片流")
    private BufferedImage image;

    /**
     * 图片流
     */
    @ApiModelProperty("图片流")
    private String encode;

    /**
     * 验证码
     */
    @ApiModelProperty("验证码")
    private String code;

    /**
     * 过期时间
     */
    @ApiModelProperty("过期时间")
    private LocalDateTime expireTime;

    /**
     * 唯一校验码
     */
    @ApiModelProperty("唯一校验码")
    private String uuidKey;

    public ImageCode(BufferedImage image, String code, Integer expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
        this.uuidKey = UUID.randomUUID().toString();
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
        this.uuidKey = UUID.randomUUID().toString();
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
