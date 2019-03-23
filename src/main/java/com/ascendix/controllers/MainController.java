package com.ascendix.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Controller
public class MainController {
    @RequestMapping("/")
    public String index(ViewControllerRegistry registry) {
        return "index.html";
    }
}
