package com.idstaa.dao;

import com.idstaa.pojo.Token;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chenjie
 * @date 2021/1/4 12:55
 */
public interface TokenDao extends JpaRepository<Token,Long> {
}
