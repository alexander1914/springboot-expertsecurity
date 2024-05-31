package com.springboot.security.expert_security.domain.security;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class IdentificacaoUsuario {
    private String id;
    private String nome;
    private String login;
    private List<String> permissoes;

    public IdentificacaoUsuario(String id, String nome, String login, List<String> permissoes) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.permissoes = permissoes;
    }

    public List<String> getPermissoes() {
        if (permissoes == null){
            permissoes = new ArrayList<>();
        }
        return permissoes;
    }
}
