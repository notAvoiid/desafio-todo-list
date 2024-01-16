package com.imrzoom.todolist.controller;

import com.imrzoom.todolist.model.Todo;
import com.imrzoom.todolist.model.TodoDTO;
import com.imrzoom.todolist.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> findAll(){
        List<Todo> todos = this.service.findAll();

        return ResponseEntity.ok().body(todos);
    }

    @PostMapping
    public ResponseEntity<List<Todo>> create(@RequestBody TodoDTO dto){
        List<Todo> todos = this.service.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(todos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Todo>> update(@PathVariable Long id, @RequestBody TodoDTO dto){
        List<Todo> todos = this.service.update(id, dto);

        return ResponseEntity.ok().body(todos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Todo>> delete(@PathVariable Long id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
