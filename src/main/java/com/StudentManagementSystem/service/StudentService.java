package com.StudentManagementSystem.service;

import java.util.List;


import com.StudentManagementSystem.entity.Student;

public interface StudentService {

	
	public List<Student> getAllStudents();
	
	void save(Student student);
	
	public Student getStudentById(int id);
	
	public void deleteStudent(int id);
}
