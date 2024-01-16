package com.imrzoom.todolist.controller;

import com.imrzoom.todolist.model.Todo;
import com.imrzoom.todolist.model.TodoDTO;
import com.imrzoom.todolist.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@Tag(name = "Todos", description = "Endpoint for managing your Todo list")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Finds all todos!", description = "Finds all todos",
    tags = {"Todos"},
    responses = {
            @ApiResponse(description = "Success", responseCode = "200",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Todo.class))
                    )
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

    })
    public ResponseEntity<List<Todo>> findAll(){
        List<Todo> todos = this.service.findAll();

        return ResponseEntity.ok().body(todos);
    }

    @Operation(summary = "Create a todo and list all of them!", description = "Create a todo and list all of them",
            tags = {"Todos"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Todo.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

            })
    @PostMapping
    public ResponseEntity<List<Todo>> create(@RequestBody @Valid TodoDTO dto){
        List<Todo> todos = this.service.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(todos);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates a todo and list all of them!", description = "Updates a todo and list all of them",
            tags = {"Todos"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Todo.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

            })
    public ResponseEntity<List<Todo>> update(@PathVariable Long id, @RequestBody TodoDTO dto){
        List<Todo> todos = this.service.update(id, dto);

        return ResponseEntity.ok().body(todos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a todo and list all of them!", description = "Deletes a todo and list all of them",
            tags = {"Todos"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Todo.class))
                                    )
                            }),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

            })
    public ResponseEntity<List<Todo>> delete(@PathVariable Long id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
