package com.example.Employee.repo;

import com.example.Employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface employeeRepo extends JpaRepository<EmployeeEntity, Long>{

    EmployeeEntity findByEmailOrContactNumberOrFirstName(String email,String number,String firstName);
}
