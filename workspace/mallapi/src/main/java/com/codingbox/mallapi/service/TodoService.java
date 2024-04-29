package com.codingbox.mallapi.service;

import com.codingbox.mallapi.domain.Todo;
import com.codingbox.mallapi.dto.PageRequestDTO;
import com.codingbox.mallapi.dto.PageResponseDTO;
import com.codingbox.mallapi.dto.TodoDTO;

public interface TodoService {
   //조회
   TodoDTO get(Long tno);
   
   //등록
   Long register(TodoDTO dto);
   
   //수정
   void modify(TodoDTO dto);
   
   //삭제
   void remove(Long tno);
   
   PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);
   /*
    *  default 키워드 : 메소드 default 키워드를 사용해서 선엄하으로써 메소드의 body, 즉 구현부를 작성할 수 있게 되었다.
    *  리액트-> 스프링-> DB로 넘어가면서 DTO를 엔티티 순으로 받
    *   어떤걸 대비해서 Default가 탄생했는지 모르겠음 
    */
 
   // Entity를 DTO로 변환하는 작업 
   default TodoDTO entityToDto(Todo todo) {
      TodoDTO todoDTO = new TodoDTO();
      todoDTO.setTno(todo.getTno());
      todoDTO.setTitle(todo.getTitle());
      todoDTO.setContent(todo.getContent());
      todoDTO.setWriter(todo.getWriter());
      // boolean은 get이 아니라 is로 가져와야함
      todoDTO.setComplete(todo.isComplete());
      todoDTO.setDueDate(todo.getDueDate());
      
      return todoDTO;
   }
   // DTO를 Entity로 변환하는 작업
   default Todo dtoToEntity(TodoDTO todoDTO) {
      Todo todo = new Todo();
      todo.setTno(todoDTO.getTno());
      todo.setTitle(todoDTO.getTitle());
      todo.setContent(todoDTO.getContent());
      todo.setWriter(todoDTO.getWriter());
      todo.setComplete(todoDTO.isComplete());
      todo.setDueDate(todoDTO.getDueDate());
      
      return todo;
   }
   
   
}
