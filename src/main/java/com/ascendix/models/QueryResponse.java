package com.ascendix.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class QueryResponse implements Serializable {
    public int count;
    public List<VSTSProject> value;
}
