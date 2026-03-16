package com.alexandre.agendadortarefas.infrastructure.business.service;

import com.alexandre.agendadortarefas.infrastructure.business.dto.TarefasDTO;
import com.alexandre.agendadortarefas.infrastructure.business.mapper.TarefasConverter;
import com.alexandre.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.alexandre.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.alexandre.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa(String token,TarefasDTO dto) {

        String email = jwtUtil.extractTokenEmail(token.substring(7));

        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatus(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);


        //Converte o DTO para entity
        //salva via repository
        //retorna o entity
        //converte novamente em DTO
        return tarefaConverter.paraTarefasDTO(tarefasRepository.save(tarefaConverter.paraTarefasEntity(dto)));

    }
}
