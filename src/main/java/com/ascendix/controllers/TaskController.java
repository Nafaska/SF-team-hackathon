package com.ascendix.controllers;

import com.ascendix.models.UIItem;
import com.ascendix.services.UIItemsService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/tasks")
public class TaskController {


    @GetMapping(value = "/getAllTaskByParams")
    public  List<UIItem> getAllTask(
            @RequestParam(value = "fromDate", required = false) Long fromDate,
            @RequestParam(value = "toDate", required = false) Long toDate,
            @RequestParam(value = "projectName", required = false) String projectName
    ) {
        List<UIItem> tfsDataTable = new ArrayList<UIItem>();
        //        Put here your code
        return tfsDataTable;
    }

    @GetMapping(value = "/getAllTaskByQueryId")
    public List<UIItem> getAllTaskByQueryId(@RequestParam(value = "queryId") String queryId) {
        List<UIItem> tfsDataTable = new ArrayList<UIItem>();
        //        Put here your code
        return new UIItemsService().getItemsByQueryId(queryId);
    }


}
