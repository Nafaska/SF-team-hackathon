package com.ascendix.controllers;

import com.ascendix.models.Option;
import com.ascendix.models.TimeFoxTask;
import com.ascendix.models.UIItem;
import com.ascendix.services.TimeFoxService;
import com.ascendix.services.UIItemsService;
import com.ascendix.services.UserAuthService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;
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
        //TODO: add functional
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
    @PostMapping(value = "/setTimeFoxData")
    public void setTimeFoxData(@RequestBody List<UIItem> vSTStasks) {

        vSTStasks.forEach(item -> {
            TimeFoxTask timeFoxTask = new TimeFoxTask();
            timeFoxTask.setTaskId(item.getProductId());
            timeFoxTask.setClientId(item.getClientId());
            timeFoxTask.setProjectId(item.getProjectId());
            timeFoxTask.setProjectId(item.getProjectId());
            timeFoxTask.setDescription(item.getDescription());
            timeFoxTask.setTime(String.valueOf(item.getSpentHours()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(item.getClosedDate());
            timeFoxTask.setMonth("03");//String.valueOf(calendar.MONTH));
            timeFoxTask.setDay("24");//String.valueOf(calendar.DAY_OF_MONTH));
            timeFoxTask.setYear("19");
            getTimeFoxService().addTask(timeFoxTask);
        });
    }

}
