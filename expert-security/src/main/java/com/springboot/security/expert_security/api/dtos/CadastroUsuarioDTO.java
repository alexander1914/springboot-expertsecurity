package com.springboot.security.expert_security.api.dtos;

import com.springboot.security.expert_security.domain.entity.Usuario;
import lombok.Data;

import java.util.List;

@Data
public class CadastroUsuarioDTO {
    private Usuario usuario;
    private List<String> permissoes;
}
