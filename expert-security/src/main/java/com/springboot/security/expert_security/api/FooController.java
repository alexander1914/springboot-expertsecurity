package com.springboot.security.expert_security.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

    @GetMapping("/public")
    public ResponseEntity<String> publicRoute(){
        return ResponseEntity.ok("Public route ok !");
    }

    @GetMapping("/private")
    public ResponseEntity<String> privateRoute(Authentication authentication){
        //TODO: Authentication -> é uma interface responsável em fazer o gerenciamento da autenticação.
        // OBS: O ideal é que nós sempre crie a nossa implementação do Authentication para coletar mais informações.
        return ResponseEntity.ok("Private route ok !, Usuário Conectado: " + authentication.getName());
    }

    //TODO: @PreAuthorize -> é uma anotation do spring para definir uma role e a anotation @EnableMethodSecurity na
    // class SecurityConfig para habilitar esse recurso.

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminRoute(){
        return ResponseEntity.ok("Admin route ok ! ");
    }
}
