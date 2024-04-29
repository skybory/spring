package com.codingbox.mallapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.codingbox.mallapi.domain.Todo;
import com.codingbox.mallapi.dto.PageRequestDTO;
import com.codingbox.mallapi.dto.PageResponseDTO;
import com.codingbox.mallapi.dto.TodoDTO;
import com.codingbox.mallapi.repository.TodoRepositoryInterface;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
	private final TodoRepositoryInterface todoRepository;

	@Override
	public TodoDTO get(Long tno) {
		Optional<Todo> result = todoRepository.findById(tno);
		Todo todo = result.orElseThrow();

		return entityToDto(todo);
	}

	@Override
	public Long register(TodoDTO dto) {
		Todo todo = dtoToEntity(dto);
		Todo result = todoRepository.save(todo);

		return result.getTno();
	}

	@Override
	public void modify(TodoDTO dto) {
		Optional<Todo> result = todoRepository.findById(dto.getTno());
		Todo todo = result.orElseThrow();

		todo.setTitle(dto.getTitle());
		todo.setContent(dto.getContent());
		todo.setWriter(dto.getWriter());
		todo.setComplete(dto.isComplete());
		todo.setDueDate(dto.getDueDate());
	}

	@Override
	public void remove(Long tno) {
		todoRepository.deleteById(tno);
	}

	@Override
	public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
		Page<Todo> result = todoRepository.search(pageRequestDTO);
		List<TodoDTO> todoList
		// result 결과값에 get() 호출해주면 todo들의 목록이 나와게 된다.
				= result.get()
						.map(todo -> entityToDto(todo))
						.collect(Collectors.toList());

		PageResponseDTO<TodoDTO> responseDTO
		= new PageResponseDTO<>(todoList, pageRequestDTO, result.getTotalElements());
	
		return responseDTO;
	}
}