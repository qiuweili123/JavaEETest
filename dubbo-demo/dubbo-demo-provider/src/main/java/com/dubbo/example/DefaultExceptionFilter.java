package com.dubbo.example;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.filter.ExceptionFilter;
import org.apache.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Activate(
        group = {CommonConstants.PROVIDER},order = -1
)
//@ControllerAdvice
//extends ExceptionFilter
//implements Filter
public class DefaultExceptionFilter extends ExceptionFilter implements Filter {
    private Logger log = LoggerFactory.getLogger(DefaultExceptionFilter.class);


    public DefaultExceptionFilter() {
        super.listener = new DefaultExceptionFilter.ExceptionListener();

    }

    static class ExceptionListener extends HandlerException implements Listener {

        private Logger logger = LoggerFactory.getLogger(ExceptionListener.class);
        private ExceptionHandlerMethodResolver resolver;
     //   private HandlerException exceptionHader;

        public ExceptionListener() {
            resolver = new ExceptionHandlerMethodResolver(this.getClass());
            //exceptionHader = new HandlerException();
        }

        @Override
        public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
            if (appResponse.hasException() && GenericService.class != invoker.getInterface()) {


                //第一种方案
                Exception exception = getException(appResponse.getException());


                Method method = resolver.resolveMethod(exception);
                try {
                    Object realResult = method.invoke(this, exception);
                    appResponse.setValue(realResult);
                    appResponse.setException(null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

                   /*
                    第二种方案
                    Throwable exception =  appResponse.getException();
                   if (exception instanceof BusinessException) {
                        BusinessException bizException = (BusinessException) exception;
                        Response response = new Response();
                        response.setData(null);
                        response.setCode(bizException.getCode());
                        response.setMsg(bizException.getMsg());
                        appResponse.setValue(response);
                        appResponse.setException(null);
                        return;
                    } else {
                        Response response = new Response();
                        response.setData(null);
                        response.setCode(CommonErrorCode.SYSTEM_INTERNAL_EXCEPTION.getCode());
                        response.setMsg(CommonErrorCode.SYSTEM_INTERNAL_EXCEPTION.getMsg());
                        appResponse.setValue(response);
                        appResponse.setException(null);
                        logger.error("Called by " + RpcContext.getContext().getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName() + ",params: " + Arrays.toString(invocation.getArguments()), exception);
                        return;
                    }*/


            }
        }

        @Override
        public void onError(Throwable e, Invoker<?> invoker, Invocation invocation) {
            logger.error("Got unchecked and undeclared exception which called by " + RpcContext.getContext().getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName() + ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
        }

        // For test purpose
        public void setLogger(Logger logger) {
            this.logger = logger;
        }

        private Exception getException(Throwable throwable) {
            Exception exception = null;
            String className = throwable.getClass().getSimpleName();
            switch (className) {
                case "BusinessException":
                    exception = (BusinessException) throwable;
                    break;

                default:
                    exception = (Exception) throwable;
            }
            return exception;
        }
    }


}
