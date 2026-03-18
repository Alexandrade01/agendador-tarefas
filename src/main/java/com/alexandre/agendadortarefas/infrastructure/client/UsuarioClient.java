package com.alexandre.agendadortarefas.infrastructure.client;

import com.alexandre.agendadortarefas.infrastructure.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usuario" , url="${usuario.url}")
public interface UsuarioClient {

    @GetMapping("usuario/getByEmail")
    UsuarioDTO getUsuarioPorEmail(@RequestParam String email, @RequestHeader("Authorization") String token);
}
