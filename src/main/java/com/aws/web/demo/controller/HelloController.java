package com.aws.web.demo.controller;

import com.aws.web.demo.controller.dto.HellodResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HellodResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HellodResponseDto(name, amount);
    }
}