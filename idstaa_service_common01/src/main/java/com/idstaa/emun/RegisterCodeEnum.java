package com.idstaa.emun;

import lombok.Data;

/**
 * @author chenjie
 * @date 2021/1/5 0:48
 */
public enum RegisterCodeEnum {
    SUCCESS(0,"注册成功"),
    DATA_NOT_NULL(1,"确实必要的参数"),
    EXIT_USER(2,"用户已存在"),
    CHECK_PARAM_ERROR(3,"参数格式错误"),
    CODE_ERROR(4,"注册码已过期，请重新获取");//这个后面必须有分号
    private int resultCode;
    private String desc;

    RegisterCodeEnum(int resultCode, String desc) {
        this.resultCode = resultCode;
        this.desc = desc;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
