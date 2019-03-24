package com.ascendix.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class UIItem {
    double spentHours;
    String description;
    Long closedDate;
}
