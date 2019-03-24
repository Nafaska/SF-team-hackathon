package com.ascendix.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
public class VSTSItemFields implements Serializable {
    private Map<String, String> fields;
    private String Title;
}
