package com.idstaa.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author chenjie
 * @date 2021/1/4 12:51
 */
@Data
@Entity
@Table(name="t_user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id; // 主键
    private String email;
    private String password;//令牌
    private Date createtime;// 注册时间
}
