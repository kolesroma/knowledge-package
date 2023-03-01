package com.aurosks.kolesnyk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

    @RequestMapping("/hello")
    public ModelAndView helloWorld() {
        String message = "Hello, World!";
        ModelAndView modelAndView = new ModelAndView("package/packages");
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
