package com.redis.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    @Serial
    private static final long serialVersionUID= 4112198546343192887L;

    private long id;
    @JsonProperty("employee_id")
    private String employeeId;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("work_dept")
    private String workDept;
}
