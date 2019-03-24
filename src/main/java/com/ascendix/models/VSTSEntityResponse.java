package com.ascendix.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class VSTSEntityResponse implements Serializable {
    public int count;
    public List<VSTSEntity> value;
    public List<VSTSEntity> workItems;
}
