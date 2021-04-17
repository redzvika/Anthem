package com.anthem.dataobjects;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class UserActions {

    private int id;


    private String time;


    private int userId;


    private int typeId;


    private String description;

    @JsonProperty("type_name")
    private String typeName;


    @JsonProperty("rfc3339_date_time")
    private String rfc3339DateTime;


}
