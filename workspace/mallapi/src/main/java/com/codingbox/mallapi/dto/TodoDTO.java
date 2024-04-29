package com.codingbox.mallapi.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

	private Long tno;
	private String title;
	private String writer;
	private String content;
	private boolean complete;
	private LocalDate dueDate;
	
}
