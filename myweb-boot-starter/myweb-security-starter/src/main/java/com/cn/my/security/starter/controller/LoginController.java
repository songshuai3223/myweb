package com.cn.my.security.starter.controller;

import com.cn.my.security.starter.dto.RestResponse;
import com.cn.my.security.starter.module.ImageCode;
import com.cn.my.security.starter.resp.UserResp;
import com.cn.my.security.starter.service.IUserService;
import com.cn.my.security.starter.util.VerifyCodeUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * @author songshuai
 */
@Api(tags = {"登录响应"}, value = "/login", produces = "application/json")
@RestController
@RequestMapping("/login")
public class LoginController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    @Autowired
    private IUserService userService;

    @GetMapping("/vercode")
    public RestResponse<String> vercode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ImageCode imgeCode = VerifyCodeUtils.getImgeCode();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(imgeCode.getImage(), "jpg", outputStream);
        String encode = Base64.getEncoder().encodeToString(outputStream.toByteArray());

        HttpSession session = req.getSession();
        imgeCode.setEncode(encode);
        imgeCode.setImage(null);
        session.setAttribute(SESSION_KEY, imgeCode);
        return RestResponse.successGet(encode);
    }

    @RequestMapping("/failure")
    public RestResponse loginfail() {
        return RestResponse.response(HttpStatus.UNAUTHORIZED, "登录失败了");
    }

    @RequestMapping("/success")
    public RestResponse loginSuccess() {
        UserResp dspUserResp = userService.getCurrentUser();
        return RestResponse.response(HttpStatus.OK, dspUserResp);
    }

    @GetMapping("/logout/success")
    public RestResponse logoutSuccess() {
        return RestResponse.response(HttpStatus.OK, "登出成功！");
    }

}
