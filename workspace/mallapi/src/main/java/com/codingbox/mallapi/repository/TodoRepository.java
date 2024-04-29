package com.codingbox.mallapi.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.codingbox.mallapi.domain.Todo;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

//@Repository
@RequiredArgsConstructor
@Transactional
public class TodoRepository {
	private final EntityManager em;
	
	public void save(Todo todo) {
		em.persist(todo);
	}
	
	public List<Todo> findAll(){
		return em.createQuery("select m from Todo m",Todo.class)
				 .getResultList();
	}
	
	public Todo findByID(Long id) {
		Todo todo =em.find(Todo.class, id);
		return todo;
	}
	
	public void update(Long id, Todo updateParam) {
		Todo findTodo =findByID(id);
		findTodo.setTitle(updateParam.getTitle());
		findTodo.setContent(updateParam.getContent());
		findTodo.setComplete(updateParam.isComplete());
		findTodo.setWriter(updateParam.getWriter());
		findTodo.setDueDate(updateParam.getDueDate());
	}
}
