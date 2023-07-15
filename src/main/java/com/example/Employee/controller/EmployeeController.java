package com.example.Employee.controller;

import com.example.Employee.dto.EmployeeDTO;
import com.example.Employee.service.impl.employeeServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {
    private final ModelMapper modelMapper;
    private final employeeServiceImpl service;

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


    @PutMapping("employeeupdate/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployees(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {

        EmployeeDTO newEmployee = service.updateEmployees(id,employeeDTO);

        return ResponseEntity.ok().body(newEmployee);
    }

    @DeleteMapping("employeedelete/{id}")
    public ResponseEntity<ApiResponse> deleteEmployees(@PathVariable(name = "id") Long id) {
        service.deleteEmployees(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Post deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("employee/email/number")
    public List<EmployeeDTO> searchbyEmailandNumber(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "number") String number)
    {

        return service.getEmployeeByEmailandNumber(email,number).stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());

    }
}
