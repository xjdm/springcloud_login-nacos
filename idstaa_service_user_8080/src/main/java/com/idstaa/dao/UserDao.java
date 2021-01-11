package com.idstaa.dao;

import com.idstaa.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chenjie
 * @date 2021/1/5 0:38
 */
public interface UserDao extends JpaRepository<User,Long> {
}
