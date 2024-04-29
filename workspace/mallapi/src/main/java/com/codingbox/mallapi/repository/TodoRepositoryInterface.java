package com.codingbox.mallapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingbox.mallapi.domain.Todo;
import com.codingbox.mallapi.repository.search.TodoSearch;

public interface TodoRepositoryInterface extends JpaRepository<Todo, Long>, TodoSearch{

}
