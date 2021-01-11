package com.idstaa.service;

import com.idstaa.pojo.AuthCode;
import com.idstaa.pojo.Token;

/**
 * @author chenjie
 * @date 2021/1/4 14:51
 */
public interface TokenService {
    Token save(Token token);

    Token findOne(Token token);
}
