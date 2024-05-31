package com.springboot.security.expert_security.api;

import com.springboot.security.expert_security.domain.repositories.GrupoRepository;
import com.springboot.security.expert_security.domain.entity.Grupo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoRepository grupoRepository;

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Grupo> salvarGrupo(@RequestBody Grupo grupo){
        grupoRepository.save(grupo);
        return ResponseEntity.ok(grupo);
    }

    @GetMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Grupo>> listarGrupos(){
        return ResponseEntity.ok(grupoRepository.findAll());
    }

}
