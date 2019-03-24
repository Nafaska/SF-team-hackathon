package com.ascendix.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TimeFoxTask {
    private String clientId;
    private String projectId;
    private String taskId;
    private String month;
    private String day;
    private String year;
    private String time;
    private String description;
}
