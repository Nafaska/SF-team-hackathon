package com.ascendix.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/tasks")
public class TaskController {

    @GetMapping(value = "/getAll")
    public String getAllTask() {
        return "hello";
    }
}
