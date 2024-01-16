package com.imrzoom.todolist.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    private Boolean realizado;
    private Integer prioridade;

    public Todo(TodoDTO dto) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
        this.realizado = dto.realizado();
        this.prioridade = dto.prioridade();
    }

    public Todo(String nome, String descricao, boolean realizado, int prioridade) {
        this.nome = nome;
        this.descricao = descricao;
        this.realizado = realizado;
        this.prioridade = prioridade;
    }
}
