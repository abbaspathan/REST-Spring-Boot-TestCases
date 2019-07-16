package com.capgemini.studentinfo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.studentinfo.entity.Student;

@Repository
public interface StudentDao extends JpaRepository<Student, Integer> {

}
