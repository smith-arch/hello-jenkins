package com.example.zx.enums;

public enum StateCodeEnum {

    SUCCESS("200","success"),

    FAIL("500","server fail");

    private String code;

    private String msg;

    private StateCodeEnum(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode(){
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }
}
