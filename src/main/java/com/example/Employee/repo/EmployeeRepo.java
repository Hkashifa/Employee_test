package com.example.Employee.repo;

import com.example.Employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long>{


    List<EmployeeEntity> findByEmailEqualsAndContactNumberEqualsAndGenderEquals(String email, String number, String gender);
    List<EmployeeEntity> findByGenderEquals(String gender);
    List<EmployeeEntity> findByEmailEquals(String email);
    List<EmployeeEntity> findByContactNumberEquals(String number);

    List<EmployeeEntity> findByGenderEqualsAndContactNumberEquals(String gender,String number);
    List<EmployeeEntity> findByEmailEqualsAndContactNumberEquals(String email,String number);
    List<EmployeeEntity> findByEmailEqualsAndGenderEquals(String email,String gender);


}

