package com.example.Employee.controller;

import com.example.Employee.dto.EmployeeDTO;
import com.example.Employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    //this works
    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees() {


        return employeeService.getAllEmployees();
    }

    @GetMapping("employee/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeesById(@PathVariable(name = "id") Long id) {
        //this works i tested it
        EmployeeDTO employeeResponse = employeeService.getEmployeesById(id);
        return ResponseEntity.ok().body(employeeResponse);
    }

    @PostMapping("/employees/new")
    public ResponseEntity<EmployeeDTO> createEmployees(@RequestBody EmployeeDTO employeeDTO) {

        EmployeeDTO newEmployee = employeeService.createEmployees(employeeDTO);
        return new ResponseEntity<>( newEmployee, HttpStatus.CREATED);

    }


    @PutMapping("employee-update/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployees(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {

        EmployeeDTO newEmployee = employeeService.updateEmployees(id,employeeDTO);

        return ResponseEntity.ok().body(newEmployee);
    }

    @DeleteMapping("employeedelete/{id}")
    public ResponseEntity<ApiResponse> deleteEmployees(@PathVariable(name = "id") Long id) {
        employeeService.deleteEmployees(id);
        return  ResponseEntity.ok().build();
    }

    @GetMapping("employeeat")
    public List<EmployeeDTO>  getEmployeeByAttribute(
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "contactNumber", required = false) String number,
            @RequestParam(name = "gender", required = false) String gender
    )
    {


        return employeeService.getEmployeeByAttribute(email,number,gender);


    }
}
