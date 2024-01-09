package com.example.demo.test;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TestController {

    private final ExampleService exampleService;

    public TestController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping("test-async")
    public String test(){
        try {
            var response = exampleService.executeString();
            System.out.println(Thread.currentThread().getName() + " " + "Executing method");
            /*if(response.isDone()){
                System.out.println(Thread.currentThread().getName() + " :: method execute finished");
            } else {
                System.out.println(Thread.currentThread().getName() +  " " + LocalDateTime.now() + " method execute not finished, responding to the client");
            }*/

            System.out.println(response);
            if(response.equals("Error")) {
                System.out.println("Execute async");
                ScheduledExecutorService executors = Executors.newScheduledThreadPool(1);
                executors.schedule(()-> {
                    try
                    {
                        exampleService.executeString();
                    } catch (InterruptedException e)
                    {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e)
                    {
                        throw new RuntimeException(e);
                    }
                }, 30, TimeUnit.SECONDS);
            }
            System.out.println("Returning from main thread");
            return response;
        } catch (Exception e) {
            return "nok";
        }
    }


    @GetMapping("test-async2")
    public String test2() throws Exception {
        System.out.println(Thread.currentThread().getName() + " " + "Executing method");
        exampleService.executeWithException();
        return "ok";
    }

}
