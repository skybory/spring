package com.codingbox.test.student.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingbox.test.student.Student;
import com.codingbox.test.student.repository.StudentRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basic/students")
public class StudentController {

	private final StudentRepository studentRepository;
	
	// 학생들 목록 불러오기
	@GetMapping
	public String students(Model model) {
	List<Student> students = studentRepository.findAll();
	model.addAttribute("students", students);
	return "basic/students";
	}
	
	// id값으로 학생 정보 불러오기
	@GetMapping("/{studentId}")
	public String student(@PathVariable long studentId, Model model) {
	Student student = studentRepository.findById(studentId);
	model.addAttribute("student",student);
	return "basic/student";
	}
	
	// 폼으로 페이지 이동 -> basic.addForm.html
	@GetMapping("/add")
	public String add() {
		return "basic/studentAddForm";
	}
	
	// 폼 제출했을 때 학생정보 전달 및 페이지 이동 -> basic.students/{studnetId}
	@PostMapping("/add")
	public String save(Student student) {
		studentRepository.save(student);
		return "redirect:/basic/students/" + student.getStudentId();
	}

	// edit 페이지 열기
	@GetMapping("/{studentId}/edit")
	public String edit(@PathVariable long studentId, Model model) {
		Student student = studentRepository.findById(studentId);
		model.addAttribute("student", student);
		return "basic/studentEditForm";
	}
	
	// edit 제출
	@PostMapping("/{studentId}/edit")
	public String edit(@PathVariable long studentId, @ModelAttribute Student student) {
		studentRepository.update(studentId, student);
		return "redirect:/basic/students/{studentId}";
	}
	
	// test용 데이터를 추가
	@PostConstruct
	public void init() {
		studentRepository.save(new Student("김자바", 20, 1, "01011112222", "강남"));
		studentRepository.save(new Student("홍길동", 70, 1, "01012345678", "강북"));
	}
}
