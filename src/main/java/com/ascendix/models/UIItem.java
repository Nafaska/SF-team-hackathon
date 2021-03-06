package com.ascendix.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UIItem {
    double spentHours;
    String description;
    Long closedDate;
    String clientId;
    String projectId;
    String productId;
}
