package com.dubbo.example.provider;

import com.dubbo.example.BusinessException;
import com.dubbo.example.CommonErrorCode;
import com.dubbo.example.DemoService;
import com.dubbo.example.ErrorCode;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl{
    private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);


    public String sayHello(String name) {

        //int b=1/0;
       if(true){
           ErrorCode errorCode=new ErrorCode(1,"project_not_exist");
            throw  new BusinessException(errorCode);
        }
        logger.info("Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "Hello " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress();
    }

}
