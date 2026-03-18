package com.alexandre.agendadortarefas.infrastructure.business.service;

import com.alexandre.agendadortarefas.infrastructure.Exceptions.ResourceNotFoundException;
import com.alexandre.agendadortarefas.infrastructure.business.dto.TarefasDTO;
import com.alexandre.agendadortarefas.infrastructure.business.mapper.TarefaUpdateConverter;
import com.alexandre.agendadortarefas.infrastructure.business.mapper.TarefasConverter;
import com.alexandre.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.alexandre.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.alexandre.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.alexandre.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTO gravarTarefa(String token, TarefasDTO dto) {

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

    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {

        return tarefaConverter.paraListaTarefasDTO(tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));

    }

    public List<TarefasDTO> buscaTarefasPorEmail(String token) {

        String email = jwtUtil.extractTokenEmail(token.substring(7));

        return tarefaConverter.paraListaTarefasDTO(tarefasRepository.findByEmailUsuario(email));

    }

    public void deletaTarefaPorId(String id) {

        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente " + id, e.getCause());
        }

    }

    public TarefasDTO alteraStatus(StatusNotificacaoEnum status, String id) {

        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não" +
                    "encontrada " + id));

            entity.setStatus(status);

            return tarefaConverter.paraTarefasDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }

    }

    public TarefasDTO updateTarefas(TarefasDTO dto, String id) {

        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não" +
                    "encontrada " + id));

            tarefaUpdateConverter.updateTarefas(dto, entity); // transforma o objeto entity

            return tarefaConverter.paraTarefasDTO(tarefasRepository.save(entity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }


    }
}

