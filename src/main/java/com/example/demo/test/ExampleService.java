package com.example.demo.test;


import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.Future;

@Service
public class ExampleService {

    @Async
    public Future<String> execute() {
        try {
            Thread.sleep(10000);
            var response = new AsyncResult<String>(LocalDateTime.now() + " | test finished");
            System.out.println(response.get());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Async
    public void executeWithException() throws Exception{
        Thread.sleep(10000);
        throw new Exception("Some exception");
    }

}
