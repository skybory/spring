package com.codingbox.mallapi.repository;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codingbox.mallapi.dto.PageRequestDTO;
import com.codingbox.mallapi.dto.TodoDTO;
import com.codingbox.mallapi.service.TodoService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoServiceTest {
   @Autowired
   TodoService todoService;
   
//   @Test
   public void testGet() {
      Long tno = 100L;
      log.info(todoService.get(tno));
      
   }
   
//   @Test
   public void testRegister() {
      TodoDTO todoDTO = new TodoDTO();
      todoDTO.setTitle("test Title");
      todoDTO.setContent("test Content");
      todoDTO.setWriter("test Writer");
      todoDTO.setDueDate(LocalDate.of(2024, 04, 29));
      log.info(todoService.register(todoDTO));
   }
   
   //내가 만들다 만거
//   @Test
   public void testModify() {
      TodoDTO todoDTO =new TodoDTO();
      todoService.modify(todoDTO);
   }
   
   @Test
   public void testGetList() {
	   PageRequestDTO pageRequestDTO = new PageRequestDTO();
	   pageRequestDTO.setPage(3);
	   log.info(todoService.getList(pageRequestDTO));
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}