package com.idstaa.service.impl;

import com.idstaa.service.IdstaaEmailService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @author chenjie
 * @date 2021/1/10 20:05
 */
@Service
public class IdstaaEmailServiceImpl implements IdstaaEmailService {
    @Autowired(required = true)
    private JavaMailSender mailSender;
    @Value("${spring.mail.from}")
    private String from;
    @Override
    public boolean sendEmail(String email, String code) {
        //创建SimpleMailMessage对象
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件发送人
        message.setFrom(from);
        //邮件接收人
        message.setTo(email.split(";"));
        //邮件主题
        message.setSubject("【idstaa】springCloud登录注册短信验证码");
        //邮件内容
        message.setText("idstaa验证码："+code);
        //发送邮件
        mailSender.send(message);
        return true;
    }
}
