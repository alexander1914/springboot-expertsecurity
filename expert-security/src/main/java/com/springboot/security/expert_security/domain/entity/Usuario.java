package com.springboot.security.expert_security.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String login;
    private String senha;
    private String nome;

    //TODO: @Transient -> é uma anotation quando você quer ingnorar o mapeamento do JPA.
    @Transient
    private List<String> permissoes;
}
