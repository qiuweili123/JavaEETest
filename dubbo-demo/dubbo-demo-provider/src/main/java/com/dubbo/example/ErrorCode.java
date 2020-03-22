package com.dubbo.example;

import java.io.Serializable;

public class ErrorCode implements Serializable {
    private Integer code;
    private String msg;

    public ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return this.msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
