package com.imrzoom.todolist.service;

import com.imrzoom.todolist.exceptions.TodoNotFoundException;
import com.imrzoom.todolist.model.Todo;
import com.imrzoom.todolist.model.TodoDTO;
import com.imrzoom.todolist.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Todo> findAll(){
        Sort sort = Sort.by(Sort.Direction.DESC, "prioridade")
                .and(Sort.by(Sort.Direction.ASC, "id"));
        return this.repository.findAll(sort);
    }

    @Transactional(readOnly = false)
    public List<Todo> create(TodoDTO dto){
        Todo todo = new Todo(dto);
        this.repository.save(todo);
        return findAll();
    }

    @Transactional(readOnly = false)
    public List<Todo> update(Long id, TodoDTO dto){
        Todo todo = this.repository.findById(id).orElseThrow(
                () -> new TodoNotFoundException(String.format("ID=%s not found!", id))
        );

        if (!dto.nome().isEmpty()) todo.setNome(dto.nome());
        if (!dto.descricao().isEmpty()) todo.setDescricao(dto.descricao());
        if (!(dto.realizado() == null)) todo.setRealizado(dto.realizado());
        if (!(dto.prioridade() == null)) todo.setPrioridade(dto.prioridade());

        this.repository.save(todo);

        return findAll();
    }

    @Transactional(readOnly = false)
    public List<Todo> delete(Long id){
        Todo todo = this.repository.findById(id).orElseThrow(
                () -> new TodoNotFoundException(String.format("ID=%s not found!", id))
        );

        this.repository.deleteById(todo.getId());
        return findAll();
    }
}
