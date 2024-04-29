package com.codingbox.mallapi.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.codingbox.mallapi.domain.QTodo;
import com.codingbox.mallapi.domain.Todo;
import com.codingbox.mallapi.dto.PageRequestDTO;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch  {

	public TodoSearchImpl() {
		super(Todo.class);
	}

	// queryDSL작업시작
	@Override
	public Page<Todo> search(PageRequestDTO pageRequestDTO) {
		log.info("====================-Search=====================");
		QTodo todo = QTodo.todo;
		JPQLQuery<Todo> query = from(todo);
//		query.where(todo.title.contains("1"));
		
		//페이징 처리를 QueryDSL의 Pageable로 사용한다.
//		Pageable pageable = PageRequest.of(1, 10, Sort.by("tno").descending());
		Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, 
										pageRequestDTO.getSize(), 
										Sort.by("tno").descending());

		this.getQuerydsl().applyPagination(pageable, query);
		
		List<Todo> list = query.fetch();	// 목록데이터 가지고 올때
		long total = query.fetchCount(); 	// 주의사항 return Long
		
		return new PageImpl<>(list, pageable, total);
	}

}
