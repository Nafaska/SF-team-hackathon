package com.ascendix.controllers;

import com.ascendix.models.VSTS_Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/tasks")
public class TaskController {

    @GetMapping(value = "/getAll")
    public ArrayList<VSTS_Data> getAllTask() {
        ArrayList<VSTS_Data> tfsDataTable = new ArrayList<VSTS_Data>();
        for (int i = 0; i < 20; i++) {
            tfsDataTable.add(new VSTS_Data((double) i, "Small Task" + i, new Date()));
        }
        return tfsDataTable;
    }
}
