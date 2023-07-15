package com.example.Employee.controller;

import org.springframework.http.HttpStatus;

public class ApiResponse {
    public ApiResponse(Boolean aTrue, String postDeletedSuccessfully, HttpStatus httpStatus) {
        System.out.println(aTrue+postDeletedSuccessfully+httpStatus);
    }
}
