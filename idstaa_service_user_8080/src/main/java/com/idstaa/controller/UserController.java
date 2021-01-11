package com.idstaa.controller;

import com.idstaa.emun.LoginCodeEnum;
import com.idstaa.emun.RegisterCodeEnum;
import com.idstaa.exception.IdstaaException;
import com.idstaa.pojo.AuthCode;
import com.idstaa.pojo.Token;
import com.idstaa.pojo.User;
import com.idstaa.resut.Result;
import com.idstaa.service.AuthCodeService;
import com.idstaa.service.TokenService;
import com.idstaa.service.UserService;
import com.idstaa.utils.MD5Utils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenjie
 * @date 2021/1/4 11:22
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AuthCodeService authCodeService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;
    /**
     * 注册接口
     *
     * @param email
     * @param password
     * @param code
     * @return true 成功，false 失败
     */
    @GetMapping(value = "/register/{email}/{password}/{code}")
    public Result register(@PathVariable String email, @PathVariable String password, @PathVariable String code) {
        // TODO 注册逻辑
        this.checkEmail(email);
        if (StringUtils.isBlank(code)) {
            throw new IdstaaException(RegisterCodeEnum.CODE_ERROR.getResultCode(),"验证码不能为空");
        }
        if (StringUtils.isBlank(password)) {
            throw new IdstaaException(RegisterCodeEnum.DATA_NOT_NULL.getResultCode(),"密码不能为空");
        }
        // 校验code
        AuthCode authCode = new AuthCode();
        authCode.setEmail(email);
        AuthCode one = authCodeService.findOne(authCode);
        if (one == null || !code.equals(one.getCode())) {
            throw new IdstaaException(RegisterCodeEnum.CODE_ERROR.getResultCode(),"注册码已过期，请重新获取");
        }
        User user = new User();
        user.setEmail(email);
        // 注册，存入用户信息到数据库
        User oldUser = userService.findOne(user);
        if (oldUser != null) {
            throw new IdstaaException(RegisterCodeEnum.EXIT_USER.getResultCode(),"用户已存在");
        }else {
            user.setPassword(password);
            // 将用户信息存入数据库
            userService.saveUser(user);
        }
        return new Result(RegisterCodeEnum.SUCCESS.getResultCode(),RegisterCodeEnum.SUCCESS.getDesc(),null);
    }

    /**
     * 是否已注册，根据邮箱判断 {true：已经注册过，false：尚未注册}
     *
     * @param email
     * @return
     */
    @GetMapping(value = "/isRegistered/{email}")
    public Result isRegistered(@PathVariable String email) {
        // TODO 判断邮箱是否注册
        this.checkEmail(email);
        User user = new User();
        user.setEmail(email);
        // 注册，存入用户信息到数据库
        User oldUser = userService.findOne(user);
        if (oldUser != null) {
            new Result(RegisterCodeEnum.SUCCESS.getResultCode(),RegisterCodeEnum.SUCCESS.getDesc(),null);
        }
        return new Result(RegisterCodeEnum.SUCCESS.getResultCode(),RegisterCodeEnum.SUCCESS.getDesc(),null);
    }

    /**
     * 登录接口，验证用户名密码合法性，根据用户名和密码生成token，token存入数据库，
     * 并写入Cookie中，登录成功返回邮箱地址，重定向到欢迎页
     *
     * @param email
     * @return
     */
    @GetMapping(value = "/login/{email}/{password}")
    public Result login(@PathVariable String email, @PathVariable String password,HttpServletResponse response) {
        // TODO 登录逻辑
        // 1、验证用户名密码合法性
        this.checkEmail(email);
        if (StringUtils.isBlank(password)) {
            throw new IdstaaException(LoginCodeEnum.DATA_NOT_NULL.getResultCode(),"密码不能为空");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        // 注册，存入用户信息到数据库
        User oldUser = userService.findOne(user);
        if(oldUser==null || !password.equals(oldUser.getPassword())){
            throw new IdstaaException(LoginCodeEnum.CHECK_PARAM_ERROR.getResultCode(),"密码错误");
        }else{
            // 生成token，存到数据库
            StringBuffer sb = new StringBuffer(email+"#@!!idstaa@#!"+password);
            // 2、根据用户名和密码生成token
            String token = MD5Utils.stringToMD5(sb.toString());
            // 3、token存入数据库
            Token tokeninfo = new Token();
            tokeninfo.setEmail(email);
            tokeninfo.setToken(token);
            tokenService.save(tokeninfo);
            // 4、写入Cookie中
            Cookie cookie=new Cookie("token",token);
            cookie.setPath("/");
            response.addCookie(cookie);
            return new Result(LoginCodeEnum.SUCCESS.getResultCode(),"登录成功",email);
            // 5、登录成功返回邮箱地址（前台重定向到欢迎页）
        }
    }

    @GetMapping(value = "/info/{token}")
    public Result getEmailByToken(@PathVariable String token) {
        Token tokenInfo = new Token();
        tokenInfo.setToken(token);
        Token one = tokenService.findOne(tokenInfo);
        if(one==null){
            throw new IdstaaException(-1,"无效的token");
        }
        return new Result(LoginCodeEnum.SUCCESS.getResultCode(),"操作成功",one.getEmail());
    }

    /**
     * 校验验证码
     *
     * @param email
     */
    private void checkEmail(@PathVariable String email) {
        // 校验验证码格式是否正确
        String regex = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";
        Matcher m = Pattern.compile(regex).matcher(email);
        if (!m.matches()) {
            throw new IdstaaException(RegisterCodeEnum.CHECK_PARAM_ERROR.getResultCode(),"邮箱校验错误，请输入正确的邮箱");
        }
    }
}
