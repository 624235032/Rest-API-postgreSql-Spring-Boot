package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.model.Department;
import com.example.repository.DepartmentRepository;

@Service
public class DepartmnetService {

	private final DepartmentRepository repository;

	public DepartmnetService(DepartmentRepository repository) {
		this.repository = repository;

	}

	public ResponseEntity<Department> cerateDepartment(Department department) {

		Department newDpm = repository
				.save(Department.builder().dpm_id(department.getDpm_id()).name(department.getName()).build());
		try {
			return new ResponseEntity<>(newDpm, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	public ResponseEntity<List<Department>> getAll() {
		try {
			return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	public ResponseEntity<Department> getDepartmentById(long id) {
		try {
			// check if employee exist in database
			Department dpmObj = getDpmId(id);

			if (dpmObj != null) {
				return new ResponseEntity<>(dpmObj, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private Department getDpmId(long id) {
		Optional<Department> dpmObj = repository.findById(id);

		if (dpmObj.isPresent()) {
			return dpmObj.get();
		}
		return null;

	}

}
