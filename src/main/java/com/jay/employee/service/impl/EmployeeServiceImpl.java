package com.jay.employee.service.impl;

import com.jay.employee.exception.UserNotFoundException;
import com.jay.employee.dto.EmployeeDTO;
import com.jay.employee.entity.Employee;
import com.jay.employee.repository.EmployeeRepository;
import com.jay.employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Mono<EmployeeDTO> create(EmployeeDTO employeeDTO) {

        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        return employeeRepository.save(employee).map(emp -> modelMapper.map(emp, EmployeeDTO.class)).log();
    }

    @Override
    public Flux<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().map(employee -> modelMapper.map(employee, EmployeeDTO.class)).log();
    }

    @Override
    public Mono<EmployeeDTO> getEmployeeById(String id) {
        return employeeRepository.findById(id).map(employee -> modelMapper.map(employee, EmployeeDTO.class)).log();
    }

    @Override
    public Mono<EmployeeDTO> updateEmployee(EmployeeDTO employeeDTO) {
        return employeeRepository.findById(employeeDTO.getId())
                .switchIfEmpty(Mono.error(new UserNotFoundException(employeeDTO.getId())))
                .flatMap(existingUser -> {
                    existingUser = modelMapper.map(employeeDTO, Employee.class);
                    return employeeRepository.save(existingUser);
                })
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class));
    }
}
