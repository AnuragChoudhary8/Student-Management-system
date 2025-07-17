package com.StudentManagementSystem.serviceImpl;

import com.StudentManagementSystem.service.StudentService;
import com.StudentManagementSystem.repository.StudentRepository;
import com.StudentManagementSystem.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

	@Override
	public void save(Student student) {
		  studentRepository.save(student);
	}

	@Override
	public Student getStudentById(int id) {
		return studentRepository.findById(id).get();
		
	}

	@Override
	public void deleteStudent(int id) {
		
		studentRepository.deleteById(id);
	}
}
