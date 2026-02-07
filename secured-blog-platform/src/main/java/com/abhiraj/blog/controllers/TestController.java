package com.abhiraj.blog.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/test")
@Hidden
public class TestController {

    @GetMapping
    public String test(){
        return "Application is working as desired";
    }

}
