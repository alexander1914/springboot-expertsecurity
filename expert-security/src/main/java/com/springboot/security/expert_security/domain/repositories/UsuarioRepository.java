package com.springboot.security.expert_security.domain.repositories;

import com.springboot.security.expert_security.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
