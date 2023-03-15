package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.model.Employees;
import com.example.repository.EmployeesRepository;

@Service
public class EmployeesService {

	private final EmployeesRepository repository;

	public EmployeesService(EmployeesRepository repository) {
		this.repository = repository;
	}

	public ResponseEntity<Employees> cerateEmployee(Employees employees) {
		Employees newEmp = repository.save(Employees.builder().name(employees.getName()).salary(employees.getSalary())
				.address(employees.getAddress()).department_id(employees.getDepartment_id()).build());
		try {
			return new ResponseEntity<>(newEmp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	public ResponseEntity<HttpStatus> deleteEmployeeById(long id) {
		try {
			// check if employee exist in database
			Employees emp = getEmpRec(id);

			if (emp != null) {
				repository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<HttpStatus> deleteAllEmployees() {
		try {
			repository.deleteAll();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	public ResponseEntity<Employees> updateEmployee(long id, Employees employees) {

		// check if employee exist in database
		Employees empObj = getEmpRec(id);

		if (empObj != null) {
			empObj.setName(employees.getName());
			empObj.setSalary(employees.getSalary());
			empObj.setAddress(employees.getAddress());
			empObj.setDepartment_id(employees.getDepartment_id());
			return new ResponseEntity<>(repository.save(empObj), HttpStatus.OK);

		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<List<Employees>> getAll() {
		try {
			return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Employees> getEmployeeById(long id) {
		try {
			// check if employee exist in database
			Employees empObj = getEmpRec(id);

			if (empObj != null) {
				return new ResponseEntity<>(empObj, HttpStatus.OK);

			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	private Employees getEmpRec(long id) {
		Optional<Employees> empObj = repository.findById(id);

		if (empObj.isPresent()) {
			return empObj.get();
		}
		return null;

	}

}
