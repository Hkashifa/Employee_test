package com.example.Employee.controller;

import com.example.Employee.dto.EmployeeDTO;
import com.example.Employee.service.impl.employeeServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class EmployeeController {
    private final ModelMapper modelMapper;
    private final employeeServiceImpl service;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);


    public EmployeeController(ModelMapper modelMapper, employeeServiceImpl service) {
        this.modelMapper = modelMapper;
        this.service = service;
    }


    //this works
    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees() {


        return service.getAllEmployees().stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("employee/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeesById(@PathVariable(name = "id") Long id) {
        //this works i tested it
        EmployeeDTO employeeResponse = service.getEmployeesById(id);
        return ResponseEntity.ok().body(employeeResponse);
    }

    @PostMapping("/employees/new")
    public ResponseEntity<EmployeeDTO> createEmployees(@RequestBody EmployeeDTO patientDto) {

        EmployeeDTO newEmployee = service.createEmployees(patientDto);
        return new ResponseEntity<>( newEmployee, HttpStatus.CREATED);

    }


    @PutMapping("employee-update/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployees(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {

        EmployeeDTO newEmployee = service.updateEmployees(id,employeeDTO);

        return ResponseEntity.ok().body(newEmployee);
    }

    @DeleteMapping("employeedelete/{id}")
    public ResponseEntity<ApiResponse> deleteEmployees(@PathVariable(name = "id") Long id) {
        service.deleteEmployees(id);
        return  ResponseEntity.ok().build();
    }

    @GetMapping("employeeat")
    public ResponseEntity<EmployeeDTO>  getEmployeeByAttribute(
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "contactNumber", required = false) String number,
            @RequestParam(name = "firstName", required = false) String firstName
    )
    {
        logger.debug("This is the email:."+email);
        EmployeeDTO employeeResponse = service.getEmployeeByAttribute(email,number,firstName);
        return ResponseEntity.ok().body(employeeResponse);
    }
}
