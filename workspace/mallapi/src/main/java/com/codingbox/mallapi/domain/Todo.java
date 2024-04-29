package com.codingbox.mallapi.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter @ Setter
@NoArgsConstructor
public class Todo {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "todoGenerator")
	@SequenceGenerator(sequenceName = "todo_seq", name = "todoGenerator", allocationSize = 1)
	private Long tno;
	
	@Column(length = 500, nullable = false)
	private String title;
	private String writer;
	private String content;
	private boolean complete;
	private LocalDate dueDate;
	
}
