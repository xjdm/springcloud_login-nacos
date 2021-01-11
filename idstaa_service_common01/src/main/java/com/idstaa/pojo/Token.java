package com.idstaa.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author chenjie
 * @date 2021/1/4 12:51
 */
@Data
@Entity
@Table(name="lagou_token")
public class Token {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id; // 主键
    private String email;
    private String token;//令牌
}
