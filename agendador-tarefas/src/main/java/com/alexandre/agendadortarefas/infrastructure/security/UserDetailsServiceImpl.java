package com.alexandre.agendadortarefas.infrastructure.security;


import com.alexandre.agendadortarefas.infrastructure.business.dto.UsuarioDTO;
import com.alexandre.agendadortarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UsuarioClient client;


    // Implementação do método para carregar detalhes do usuário pelo e-mail
    public UserDetails loadUserByUsername(String email, String token){

        UsuarioDTO usuarioDTO = client.getUsuarioPorEmail(email,token);

        return User
                .withUsername(usuarioDTO.getEmail())
                .password(usuarioDTO.getSenha())
                .build();

    }
}
