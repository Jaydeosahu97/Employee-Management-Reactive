package com.jay.employee;

import com.jay.employee.dto.EmployeeDTO;
import com.jay.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeHandler {

    private final EmployeeService employeeService;

    public EmployeeHandler(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public Mono<EmployeeDTO> create(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.create(employeeDTO).log();
    }

    @GetMapping("/all")
    public Flux<EmployeeDTO> getAllEmployee() {
        return employeeService.getAllEmployees().log();
    }

    @GetMapping("/{id}")
    public Mono<EmployeeDTO> getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployeeById(id).log();
    }

    @PutMapping("/update")
    public Mono<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(employeeDTO).log();
    }
}
