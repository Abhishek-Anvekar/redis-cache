package com.javaguides.employee_service.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto implements Serializable { // we implemented Serializable before doing value serialization config in CacheConfig class.
    //So, now your objects are stored in Redis as readable JSON.
    // so you do NOT need to implement Serializable in your DTO

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String departmentCode;
}
