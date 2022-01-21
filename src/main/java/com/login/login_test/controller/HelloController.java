package com.login.login_test.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping(value = "/helloworld/string")
    @ResponseBody
    public String hellowordString() {
        return "helloworld";
    }

    @GetMapping(value = "/helloworld/json")
    @ResponseBody
    public Hello helloworldJson() {
        Hello hello = new Hello();
        hello.message = "helloworld";
        return hello;
    }

    @Getter
    @Setter
    public static class Hello {
        private String message;
    }


}
