package com.idstaa.controller;

import com.idstaa.pojo.AuthCode;
import com.idstaa.service.AuthCodeService;
import com.idstaa.service.IdstaaEmailService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenjie
 * @date 2021/1/4 13:15
 */
@RestController
@RequestMapping("/code")
public class CodeController {
    @Reference
    private IdstaaEmailService idstaaEmailService;
    @Autowired
    private AuthCodeService authCodeService;
    @GetMapping(value = "/create/{email}")
    public boolean createCodeAndSendEmail(@PathVariable String email){
        checkEmail(email);
        // 6位验证码
        String code = UUID.randomUUID().toString().substring(0,6);

        AuthCode authCode = new AuthCode();
        authCode.setEmail(email);
        AuthCode one = authCodeService.findOne(authCode);
        if(one!=null && one.getExpiretime().getTime()>new Date().getTime()){
            throw new RuntimeException("1分钟内请勿重新获取验证码");
        }else{
            AuthCode newAuthCode = new AuthCode();
            newAuthCode.setCode(code);
            newAuthCode.setCreatetime(new Date());
            newAuthCode.setExpiretime(new Date(new Date().getTime()+60*10*1000));
            newAuthCode.setEmail(email);
            if(idstaaEmailService.sendEmail(email, code)){
                authCodeService.saveAuthCode(newAuthCode);
            }else {
                throw new RuntimeException("验证码发送失败");
            }
        }
        // 验证码存入数据库
        return true;
    }

    /**
     * 校验验证码
     * @param email
     */
    private void checkEmail(@PathVariable String email) {
        // 校验验证码格式是否正确
        String regex = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";
        Matcher m = Pattern.compile(regex).matcher(email);
        if (!m.matches()){
            throw new RuntimeException("邮箱校验错误，请输入正确的邮箱");
        }
    }

    @GetMapping(value = "/validate/{email}/{code}")
    public boolean validateCodeWithEmail(@PathVariable String email, @PathVariable String code){
        // 校验验证码
        this.checkEmail(email);
        AuthCode authCode = new AuthCode();
        authCode.setEmail(email);
        AuthCode one = authCodeService.findOne(authCode);
        return one != null && one.getCode().equals(code);
    }
}
