package com.ascendix.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TimeFoxUser {
    private String userName;
    private String password;
    private String orgId;
}
