package com.springboot.security.expert_security.api;

import com.springboot.security.expert_security.api.dtos.CadastroUsuarioDTO;
import com.springboot.security.expert_security.domain.entity.Usuario;
import com.springboot.security.expert_security.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody CadastroUsuarioDTO cadastroUsuarioDTO){
        Usuario novoUsuario = usuarioService.salvar(cadastroUsuarioDTO.getUsuario(), cadastroUsuarioDTO.getPermissoes());
        return ResponseEntity.ok(novoUsuario);
    }
}
