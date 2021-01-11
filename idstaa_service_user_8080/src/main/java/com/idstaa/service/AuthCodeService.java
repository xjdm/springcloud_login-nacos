package com.idstaa.service;

import com.idstaa.pojo.AuthCode;

/**
 * @author chenjie
 * @date 2021/1/4 14:51
 */
public interface AuthCodeService {
    AuthCode saveAuthCode(AuthCode authCode);

    AuthCode findOne(AuthCode authCode);
}
