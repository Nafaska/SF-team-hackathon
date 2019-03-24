package com.ascendix.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class VSTS_Data {
    double spendedTime;
    String description;
    Date workDay;
}
