package com.alexandre.agendadortarefas.infrastructure.security;


import com.alexandre.agendadortarefas.infrastructure.business.dto.UsuarioDTO;
import com.alexandre.agendadortarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UsuarioClient client;


    // Implementação do método para carregar detalhes do usuário pelo e-mail
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        // Busca o usuário no banco de dados pelo e-mail
//        Usuario usuario;
//
//			usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new ConflictException("Usuario não encontrado: "+email));
//
//
//        // Cria e retorna um objeto UserDetails com base no usuário encontrado
//        return org.springframework.security.core.userdetails.User
//                .withUsername(usuario.getEmail()) // Define o nome de usuário como o e-mail
//                .password(usuario.getSenha()) // Define a senha do usuário
//                .build(); // Constrói o objeto UserDetails
//    }

    public UserDetails loadUserByUsername(String email, String token){

        UsuarioDTO usuarioDTO = client.getUsuarioPorEmail(email,token);

        return User
                .withUsername(usuarioDTO.getEmail())
                .password(usuarioDTO.getSenha())
                .build();

    }
}
