package com.springboot.security.expert_security.domain.service;

import com.springboot.security.expert_security.domain.entity.Grupo;
import com.springboot.security.expert_security.domain.entity.Usuario;
import com.springboot.security.expert_security.domain.entity.UsuarioGrupo;
import com.springboot.security.expert_security.domain.repositories.GrupoRepository;
import com.springboot.security.expert_security.domain.repositories.UsuarioGrupoRepository;
import com.springboot.security.expert_security.domain.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private UsuarioGrupoRepository usuarioGrupoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario salvar(Usuario usuario, List<String> grupos) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        usuarioRepository.save(usuario);

        List<UsuarioGrupo> listaUsuarioGrupo = grupos.stream().map(nomeGrupo -> {
                    Optional<Grupo> possivelGrupo = grupoRepository.findByNome(nomeGrupo);
                    if (possivelGrupo.isPresent()) {
                        Grupo grupo = possivelGrupo.get();
                        return new UsuarioGrupo(usuario, grupo);
                    }
                    return null;
                })
                .filter(grupo -> grupo != null)
                .collect(Collectors.toList());

        usuarioGrupoRepository.saveAll(listaUsuarioGrupo);

        return usuario;
    }

    public Usuario obterUsuarioComPermissoes(String login){
        Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(login);
        if(usuarioOptional.isEmpty()){
            return null;
        }

        Usuario usuario = usuarioOptional.get();
        List<String> permissioes = usuarioGrupoRepository.findPermissioesByUsuario(usuario);
        usuario.setPermissoes(permissioes);

        return usuario;
    }

}
