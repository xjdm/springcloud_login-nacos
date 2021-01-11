package com.idstaa.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author chenjie
 * @date 2021/1/5 0:58
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ResultStatusEnum {
    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功！"),

    /**
     * 密码错误
     */
    PASSWORD_NOT_MATCHING(400, "密码错误");

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;
}