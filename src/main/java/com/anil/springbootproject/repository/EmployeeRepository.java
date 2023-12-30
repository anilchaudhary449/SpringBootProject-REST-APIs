package com.anil.springbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anil.springbootproject.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{
	

}
