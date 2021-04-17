package com.anthem.dataobjects;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    int id;
    private String firstName;
    private String lastName;
    private String program;
}
