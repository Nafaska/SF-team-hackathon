package com.ascendix.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/tasks")
public class TaskController {

    @GetMapping(value = "/getAllTaskByParams")
    public String getAllTask(
            @RequestParam(value = "fromDate", required = false) Long fromDate,
            @RequestParam(value = "toDate", required = false) Long toDate,
            @RequestParam(value = "projectName", required = false) String projectName
    ) {
        System.out.println(fromDate);
        return fromDate.toString();
    }

    @GetMapping(value = "/getAllTaskByQueryId")
    public String getAllTaskByQueryId(@RequestParam(value = "queryId") String queryId) {
        return null;
    }
}
