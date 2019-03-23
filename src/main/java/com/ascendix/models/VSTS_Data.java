package com.ascendix.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

public class VSTS_Data {
    @Getter
    @Setter
    double spendedTime;
    @Getter
    @Setter
    String description;
    @Getter
    @Setter
    Date workDay;

    public VSTS_Data (Double spendedTime,String description,Date workDay) {
        this.spendedTime = spendedTime;
        this.description = description;
        this.workDay = workDay;
    }
}
