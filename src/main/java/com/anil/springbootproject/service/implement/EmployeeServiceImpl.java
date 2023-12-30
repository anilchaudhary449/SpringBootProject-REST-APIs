package com.anil.springbootproject.service.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anil.springbootproject.exception.ResourceNotFoundException;
import com.anil.springbootproject.model.Employee;
import com.anil.springbootproject.repository.EmployeeRepository;
import com.anil.springbootproject.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	
	private EmployeeRepository employeeRepository;
	
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}


	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}


	@Override
	public List<Employee> getAllEmployee() {
		
		return employeeRepository.findAll();
	}


	@Override
	public Employee getEmployeeById(long id) {
		

		return employeeRepository.findById(id).orElseThrow(()->
								new ResourceNotFoundException("Employee","Id",id));
	}


	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// we need to check whether the employee with given id is already exist in DB or not
		
		Employee existingEmployee=employeeRepository.findById(id).orElseThrow(
									()->new ResourceNotFoundException("Employee","Id",id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		//save existing employee to DB
		
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}


	@Override
	public void deleteEmployee(long id) {
		
		//check whether the employee exist in DB or not
		employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("employee","Id",id));
		employeeRepository.deleteById(id);
		
	}
	
}