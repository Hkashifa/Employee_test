package com.example.Employee.service.impl;

import com.example.Employee.dto.EmployeeDTO;
import com.example.Employee.entity.EmployeeEntity;
import com.example.Employee.repo.employeeRepo;
import com.example.Employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class employeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(employeeServiceImpl.class);

    private final ModelMapper modelMapper;
    private final employeeRepo employeeRepository;

    public employeeServiceImpl(ModelMapper modelMapper, employeeRepo employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeEntity> getAllEmployees() {

        return employeeRepository.findAll();
    }

    @Override
    public EmployeeDTO createEmployees(EmployeeDTO employeeDTO) {

        EmployeeEntity employeeConverted = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeRepository.save(employeeConverted);

        EmployeeDTO employeeResponse = modelMapper.map(employeeConverted, EmployeeDTO.class);
        return employeeResponse;

    }

    @Override
    public EmployeeDTO updateEmployees(Long id, EmployeeDTO employeeDTO) {

        Optional<EmployeeEntity> updated_employee = employeeRepository.findById(id);

        if (updated_employee.isPresent()) {
            EmployeeEntity EmployeeEntity = updated_employee.get();

            EmployeeEntity.setFirstName(employeeDTO.getFirstName());
            EmployeeEntity.setLastName(employeeDTO.getLastName());
            EmployeeEntity.setGender(employeeDTO.getGender());
            EmployeeEntity.setEmail(employeeDTO.getEmail());
            EmployeeEntity.setAge(employeeDTO.getAge());
            EmployeeEntity.setAddress(employeeDTO.getAddress());
            EmployeeEntity.setContactNumber(employeeDTO.getContactNumber());

            employeeRepository.save(EmployeeEntity);//
            // entity to DTO
            EmployeeDTO employeeResponse = modelMapper.map(EmployeeEntity, EmployeeDTO.class);
            return employeeResponse;
        }
        return null;
    }

    @Override
    public void deleteEmployees(long id) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        EmployeeEntity employee = optionalEmployee
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));

        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeDTO getEmployeesById(long id) {

        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        EmployeeEntity employee = optionalEmployee
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO getEmployeeByAttribute(String email,String number,String firstName) {

        logger.info("This is the email:." + email);
        EmployeeEntity employee = employeeRepository.findByEmailOrContactNumberOrFirstName(email,number,firstName);
        //System.out.println(email);
        return modelMapper.map(employee, EmployeeDTO.class);

    }
}


