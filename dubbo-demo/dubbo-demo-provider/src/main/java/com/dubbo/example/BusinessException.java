package com.dubbo.example;

import org.apache.dubbo.common.utils.StringUtils;

public class BusinessException extends RuntimeException {
    private ErrorCode errorCode;
    private Integer code;
    private String msg;
    private Object[] args;

    public BusinessException() {
        super();
    }

    public BusinessException(ErrorCode errorCode) {
        this(errorCode, new Object[0]);
    }

    public BusinessException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(Integer code, String msg, Object... args) {
        this.code = code;
        this.msg = msg;
        this.args = args;
    }

    public BusinessException(ErrorCode errorCode, Object arg) {
        this(errorCode, new Object[]{arg});
    }

    public BusinessException(ErrorCode errorCode, Object... args) {
        super();
        this.errorCode = errorCode;
        if (errorCode != null) {
            this.code = errorCode.getCode();
            this.msg = errorCode.getMsg();
        }
        if (args == null) {
            this.args = new Object[0];
        } else {
            this.args = args;
        }
    }

    public Integer getCode() {
        return this.code;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public Object[] getArgs() {
        return this.args;
    }


    public String getMessage() {
        StringBuilder exMessage = new StringBuilder();
        exMessage.append("code: ");
        exMessage.append(this.code);
        exMessage.append("; message: ");
        exMessage.append(getMsg());
        return exMessage.toString();
    }


    public String getMsg() {
        if (StringUtils.isNotEmpty(this.msg)) {
            return String.format(this.msg, this.args);
        }
        return msg;
    }

}