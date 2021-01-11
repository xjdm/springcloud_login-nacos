package com.idstaa.service;

import com.idstaa.pojo.User;
/**
 * @author chenjie
 * @date 2021/1/4 14:51
 */
public interface UserService {
    User saveUser(User user);

    User findOne(User user);
}
