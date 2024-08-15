package com.jay.employee.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
public class EmployeeDTO implements Serializable {
    String id;
    @NotBlank
    @Length(min = 3)
    String name;
}
