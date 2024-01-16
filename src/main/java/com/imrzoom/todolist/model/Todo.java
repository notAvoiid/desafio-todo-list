package com.imrzoom.todolist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Todos")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Boolean realizado;
    private Integer prioridade;

    public Todo(TodoDTO dto) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
        this.realizado = dto.realizado();
        this.prioridade = dto.prioridade();
    }
}
