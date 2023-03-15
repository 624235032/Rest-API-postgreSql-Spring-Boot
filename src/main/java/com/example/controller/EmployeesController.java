package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employees;
import com.example.service.EmployeesService;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

	private final EmployeesService service;

	public EmployeesController(EmployeesService service) {
		this.service = service;
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Employees>> getAll() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employees> getEmployeeById(@PathVariable("id") long id) {
		return service.getEmployeeById(id);
	}

	@PostMapping("/add")
	public ResponseEntity<Employees> cerateEmployee(@RequestBody Employees employees) {
		return service.cerateEmployee(employees);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable("id") long id) {
		return service.deleteEmployeeById(id);
	}

	@DeleteMapping("/DelAll")
	public ResponseEntity<HttpStatus> deleteAllEmployees() {
		return service.deleteAllEmployees();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employees> updateEmployee(@PathVariable("id") long id, @RequestBody Employees employees) {
		return service.updateEmployee(id, employees);

	}

}
