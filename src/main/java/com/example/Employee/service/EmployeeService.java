package com.example.Employee.service;

import com.example.Employee.dto.EmployeeDTO;
import com.example.Employee.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

    List<EmployeeEntity> getAllEmployees();

    EmployeeDTO createEmployees(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployees(Long id, EmployeeDTO employeeDTO);
    void deleteEmployees(long id);
    EmployeeDTO getEmployeesById(long id);
    public List<EmployeeDTO> getEmployeeByAttribute(String email,String number,String gender) ;
}
