package com.codingbox.mallapi.domain.controller.advice;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

// 공통으로 처리할 Advice Class 
@RestController
public class CustomControllerAdvice {

	@ExceptionHandler(NoSuchFieldException.class)
	public ResponseEntity<?> notExist(NoSuchFieldException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg" , e.getMessage()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> notExist(MethodArgumentNotValidException e){
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Map.of("msg", e.getMessage()));
	}
}
