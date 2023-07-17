package com.example.Employee.service;

import com.example.Employee.dto.EmployeeDTO;
import com.example.Employee.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

   List<EmployeeDTO> getAllEmployees();
    EmployeeDTO createEmployees(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployees(Long id, EmployeeDTO employeeDTO);
    void deleteEmployees(long id);
    EmployeeDTO getEmployeesById(long id);
    List<EmployeeDTO> getEmployeeByAttribute(String email,String number,String gender) ;
}
