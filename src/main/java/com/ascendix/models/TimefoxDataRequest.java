package com.ascendix.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class TimefoxDataRequest {
    List<UIItem> uiItems;
}
