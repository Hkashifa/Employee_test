package com.example.Employee.dto;

import lombok.Data;

@Data
public class EmployeeDTO extends basedto{

    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String age;
    private String address;
    private String contactNumber;
}
