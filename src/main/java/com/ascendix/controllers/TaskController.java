package com.ascendix.controllers;

import com.ascendix.models.TFS_QueryParameters;
import com.ascendix.models.VSTS_Data;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/tasks")
public class TaskController {


    @GetMapping(value = "/getAllTaskByParams")
    public  ArrayList<VSTS_Data> getAllTask(
            @RequestParam(value = "fromDate", required = false) Long fromDate,
            @RequestParam(value = "toDate", required = false) Long toDate,
            @RequestParam(value = "projectName", required = false) String projectName
    ) {
        ArrayList<VSTS_Data> tfsDataTable = new ArrayList<VSTS_Data>();
        //        Put here your code
        return tfsDataTable;
    }

    @GetMapping(value = "/getAllTaskByQueryId")
    public ArrayList<VSTS_Data> getAllTaskByQueryId(@RequestParam(value = "queryId") String queryId) {
        ArrayList<VSTS_Data> tfsDataTable = new ArrayList<VSTS_Data>();
        for (int i = 0; i < 20; i++) {
            tfsDataTable.add(new VSTS_Data((double) i, "Small Task" + i, new Date()));
        }
        return tfsDataTable;
    }


}
