package com.idstaa.emun;

/**
 * @author chenjie
 * @date 2021/1/5 1:45
 */
public enum  LoginCodeEnum {
    SUCCESS(0,"登录成功"),
    DATA_NOT_NULL(1,"确实必要的参数"),
    FAIl(2,"登录失败"),
    CHECK_PARAM_ERROR(3,"参数格式错误");//这个后面必须有分号
    private int resultCode;
    private String desc;

    LoginCodeEnum(int resultCode, String desc) {
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
