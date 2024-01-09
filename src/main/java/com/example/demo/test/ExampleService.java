package com.example.demo.test;


import java.util.concurrent.ExecutionException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.Future;

@Service
public class ExampleService {

    @Async
    @Retryable(retryFor = Exception.class, backoff = @Backoff(delay = 10000), recover = "recoverMethod")
    public Future<String> execute() throws InterruptedException, ExecutionException
    {
        //try {
            System.out.println(Thread.currentThread().getName() + " " + "Executing task");
            var response = new AsyncResult<>(Thread.currentThread().getName() + " " + LocalDateTime.now() + " | test finished");
            System.out.println(response.get());
            throw new RuntimeException();
            //return response;
        /*} catch (Exception e) {
            e.printStackTrace();
            return null;
        }*/
    }

    @Retryable(retryFor = Exception.class, backoff = @Backoff(delay = 10000), recover = "recoverMethod")
    public String executeString() throws InterruptedException, ExecutionException
    {
        //try {
        System.out.println(Thread.currentThread().getName() + " " + "Executing task");
        var response = new AsyncResult<>(Thread.currentThread().getName() + " " + LocalDateTime.now() + " | test finished");
        System.out.println(response.get());
        throw new RuntimeException();
        //return response;
        /*} catch (Exception e) {
            e.printStackTrace();
            return null;
        }*/
    }


    @Async
    public void executeWithException() throws Exception{
        System.out.println(Thread.currentThread().getName() + " " + "Executing task");
        Thread.sleep(10000);
        throw new Exception("Some exception");
    }

    /*@Async
    @Recover
    public Future<String> recoverMethod(RuntimeException ex) throws Exception
    {
        System.out.println(Thread.currentThread().getName() + " :: recovered");
        throw new Exception("Some exception");
    }*/

    @Recover
    public String recoverMethod(RuntimeException ex) throws Exception
    {
        System.out.println(Thread.currentThread().getName() + " :: recovered");
        return "Error";
    }

}
