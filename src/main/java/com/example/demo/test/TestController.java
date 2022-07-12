package com.example.demo.test;


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
            var response = exampleService.execute();
            if(response.isDone()){
                System.out.println("method execute finished");
            } else {
                System.out.println(LocalDateTime.now() + " method execute not finished, responding to the client");
            }
            return "ok";
        } catch (Exception e) {
            return "nok";
        }
    }

    @GetMapping("test-async2")
    public String test2() throws Exception {
        exampleService.executeWithException();
        return "ok";
    }

}
