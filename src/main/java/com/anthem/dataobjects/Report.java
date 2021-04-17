package com.anthem.dataobjects;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    int id;
    private String name;
    private LocalDate date;
    private String message;
}
