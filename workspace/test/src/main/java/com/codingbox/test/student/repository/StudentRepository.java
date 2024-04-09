package com.codingbox.test.student.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.codingbox.test.student.Student;

import jakarta.annotation.PostConstruct;
@Repository
public class StudentRepository {

	private static final Map<Long, Student> store = new HashMap<>();
	private static long sequence = 0L;
	

	// Student를 리턴할 필요가 있나?
	public Student save(Student student) {
		student.setStudentId(++sequence);
		store.put(student.getStudentId(), student);
		return student;
	}

	public List<Student> findAll(){
		return new ArrayList<Student>(store.values());
	}

	public Student findById(long studentId) {
		
		return store.get(studentId);
	}

	public void update(long studentId, Student newStudent) {
		Student student = findById(studentId);
		student.setStudentName(newStudent.getStudentName());
		student.setAge(newStudent.getAge());
		student.setSubject(newStudent.getSubject());
		student.setPhone(newStudent.getPhone());
	}
}
