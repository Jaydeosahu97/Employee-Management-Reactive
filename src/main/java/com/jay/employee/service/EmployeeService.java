package com.jay.employee.service;

import com.jay.employee.dto.EmployeeDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Mono<EmployeeDTO> create(EmployeeDTO employeeDTO);

    Flux<EmployeeDTO> getAllEmployees();

    Mono<EmployeeDTO> getEmployeeById(String id);

    Mono<EmployeeDTO> updateEmployee(EmployeeDTO employeeDTO);
}
