package com.capgemini.studentinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.capgemini.studentinfo.entity.Student;
import com.capgemini.studentinfo.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService service;

	@PostMapping("/")
	public ResponseEntity<Student> addNewStudent(@RequestBody Student student) {
		Student student2 = service.addNewStudent(student);
		return new ResponseEntity<Student>(student2, HttpStatus.CREATED);
	}

	@GetMapping("/{rollNumber}")
	public ResponseEntity<Student> getStudentInfo(@PathVariable int rollNumber) {
		return new ResponseEntity<Student>(service.findStudentDetail(rollNumber), HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<Student> updateStudentDetails(@RequestBody Student student) {
		return new ResponseEntity<Student>(service.updateDetails(student), HttpStatus.OK);
	}

	@DeleteMapping("/{rollNumber}")
	public ResponseEntity<String> deleteStudentDetail(@PathVariable int rollNumber) {
		return new ResponseEntity<String>("Recored Deleted Successfully",HttpStatus.OK);
	}
}
