package com.dubbo.example;


public interface CommonErrorCode {

    ErrorCode DATABASE_OPERATION_EXCEPTION = new ErrorCode(1, "Db Operation Error");
    ErrorCode PARAMETER_EXCEPTION = new ErrorCode(2, "");
    ErrorCode SYSTEM_INTERNAL_EXCEPTION = new ErrorCode(3, "Intenal Server Error ");
    ErrorCode INVALID_TOKEN = new ErrorCode(4, "Invalid Token");
    ErrorCode OPERATIOIN_FAIL = new ErrorCode(5, "Operation Fail");

}
