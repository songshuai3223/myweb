package com.cn.my.common.starter.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author songshuai
 * @date 2021/3/4-15:19
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DspException extends RuntimeException {

    /**
     * 错误码
     */
    private HttpStatus status;

    /**
     * @param status
     * @param message 异常信息
     */
    public DspException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    /**
     * 默认 400异常
     *
     * @param message
     */
    public DspException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }
}
