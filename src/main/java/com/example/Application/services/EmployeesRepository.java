package com.example.Application.services;

import com.example.Application.models.employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<employee, Integer> {

}
