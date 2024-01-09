package com.example.demo.test;


import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import java.lang.reflect.Method;


public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
        System.out.println(Thread.currentThread().getName() + " :: Message from exception - " + throwable.getMessage());
        System.out.println(Thread.currentThread().getName() + " :: Method name " + method.getName());
    }

}