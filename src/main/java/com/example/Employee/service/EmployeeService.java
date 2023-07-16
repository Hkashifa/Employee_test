package com.example.Employee.service;

import com.example.Employee.dto.EmployeeDTO;
import com.example.Employee.entity.employeeEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EmployeeService {

    List<employeeEntity> getAllEmployees();

    public EmployeeDTO createEmployees(EmployeeDTO employeeDTO);

    public EmployeeDTO updateEmployees(Long id, EmployeeDTO employeeDTO);

    public void deleteEmployees(long id);

    EmployeeDTO getEmployeesById(long id);

    public EmployeeDTO getEmployeeByEmail(String email);
}
