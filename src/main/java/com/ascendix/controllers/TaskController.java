package com.ascendix.controllers;

import com.ascendix.models.Option;
import com.ascendix.models.UIItem;
import com.ascendix.services.TimeFoxService;
import com.ascendix.services.UIItemsService;
import com.ascendix.services.UserAuthService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/tasks")
public class TaskController {
    private static TimeFoxService TIME_FOX_SERVICE;

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
        return new UIItemsService().getItemsByQueryId(queryId);
    }

    @GetMapping(value = "/getClients")
    public List<Option> getClients() {
        return getTimeFoxService().getClients();
    }

    @GetMapping(value = "/getProjects")
    public List<Option> getProjectsByClient(
            @RequestParam(value = "clientId", required = true) String clientId
    ) {
        return getTimeFoxService().getProjectsByClient(clientId);
    }

    @GetMapping(value = "/getTasks")
    public List<Option> getTaskByClient(
            @RequestParam(value = "clientId", required = true)String clientId,
            @RequestParam(value = "projectId", required = true)String projectId
    ) {
        return getTimeFoxService().getTasksByProject(projectId, clientId);
    }

    private TimeFoxService getTimeFoxService(){
        return TIME_FOX_SERVICE == null ? new TimeFoxService(new UserAuthService().getUser()) : TIME_FOX_SERVICE;
    }

}
