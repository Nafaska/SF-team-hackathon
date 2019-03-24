package com.ascendix.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class System implements Serializable {
    private String WorkItemType;
    private String Title;
}
