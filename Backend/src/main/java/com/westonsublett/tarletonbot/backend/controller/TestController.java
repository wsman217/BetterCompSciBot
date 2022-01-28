package com.westonsublett.tarletonbot.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
This is only a temporary class so I can check that everything is running properly without having any real controllers yet.
Will most likely be used to check the databases also.
 */
@RestController
public class TestController {

    @GetMapping("test")
    public String test() {
        return "test";
    }
}
