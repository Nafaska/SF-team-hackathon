package com.ascendix.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FormInitData {
    List<Option> clients;
    List<Option> projects;
    List<Option> tasks;
}
