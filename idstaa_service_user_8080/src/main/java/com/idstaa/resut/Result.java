package com.idstaa.resut;

import lombok.Data;

/**
 * @author chenjie
 * @date 2021/1/5 1:06
 */
@Data
public class Result {
    private int code;
    private String message;
    private Object results;

    public Result(int code, String message,Object results) {
        this.code = code;
        this.message = message;
        this.results = results;
    }
}
