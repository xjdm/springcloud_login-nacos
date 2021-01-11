package com.idstaa.dao;

import com.idstaa.pojo.AuthCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chenjie
 * @date 2021/1/4 12:55
 */
public interface AuthCodeDao extends JpaRepository<AuthCode,Long>{
}
