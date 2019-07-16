package com.capgemini.studentinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.studentinfo.dao.StudentDao;
import com.capgemini.studentinfo.entity.Student;

@Service
public class StudentService {

	@Autowired
	private StudentDao dao;

	public Student addNewStudent(Student student) {
		return dao.save(student);
	}

	public Student findStudentDetail(int rollNumber) {
		return dao.findById(rollNumber).get();
	}

	public Student updateDetails(Student student) {
		return dao.save(student);
	}

	public void deleteStudentDetail(int rollNumber) {
		dao.deleteById(rollNumber);
	}
}
