package com.idstaa.exception;

import lombok.Getter;

/**
 * @author chenjie
 * @date 2021/1/5 0:57
 */
@Getter
public class IdstaaException extends RuntimeException {
    private int code;
    private String message;

    public IdstaaException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public IdstaaException(ResultStatusEnum resultStatusEnum) {
        this.code = resultStatusEnum.getCode();
        this.message = resultStatusEnum.getMessage();
    }
}