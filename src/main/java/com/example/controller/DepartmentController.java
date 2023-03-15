package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Department;
import com.example.service.DepartmnetService;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	private final DepartmnetService service;

	public DepartmentController(DepartmnetService service) {
		this.service = service;
	}

	@PostMapping("/add")
	public ResponseEntity<Department> cerateDepartment(@RequestBody Department department) {
		return service.cerateDepartment(department);

	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Department>> getAll() {
		return service.getAll();
	}

}
