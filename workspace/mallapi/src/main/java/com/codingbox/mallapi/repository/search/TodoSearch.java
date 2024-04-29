package com.codingbox.mallapi.repository.search;

import org.springframework.data.domain.Page;

import com.codingbox.mallapi.domain.Todo;
import com.codingbox.mallapi.dto.PageRequestDTO;

public interface TodoSearch {
	Page<Todo> search(PageRequestDTO pageRequestDTO);
	
}
