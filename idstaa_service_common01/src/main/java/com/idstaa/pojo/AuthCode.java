package com.idstaa.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author chenjie
 * @date 2021/1/4 12:48
 */
@Data
@Entity
@Table(name="lagou_auth_code")
public class AuthCode {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id; // 主键
    private String email;
    private String code;
    private Date createtime;
    private Date expiretime;


}
