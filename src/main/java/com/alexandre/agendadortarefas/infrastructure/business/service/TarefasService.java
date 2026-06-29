package com.alexandre.agendadortarefas.infrastructure.business.service;

import com.alexandre.agendadortarefas.infrastructure.Exceptions.ResourceNotFoundException;
import com.alexandre.agendadortarefas.infrastructure.business.dto.TarefasDTO;
import com.alexandre.agendadortarefas.infrastructure.business.dto.TarefasDTORecord;
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

    public TarefasDTORecord gravarTarefa(String token, TarefasDTORecord dto) {

        String email = jwtUtil.extrairEmailToken(token.substring(7));

        TarefasDTORecord dtoFinal = new TarefasDTORecord(null,dto.nomeTarefa(),dto.descricao(),LocalDateTime.now(),
                dto.dataEvento(),email,null, StatusNotificacaoEnum.PENDENTE);



        //Converte o DTO para entity
        //salva via repository
        //retorna o entity
        //converte novamente em DTO
        return tarefaConverter.paraTarefasDTORecord(tarefasRepository.save(tarefaConverter.paraTarefasEntity(dtoFinal)));

    }

    public List<TarefasDTORecord> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {

        return tarefaConverter.paraListaTarefasDTORecord(tarefasRepository
                .findByDataEventoBetweenAndStatus(dataInicial, dataFinal,StatusNotificacaoEnum.PENDENTE));

    }

    public List<TarefasDTORecord> buscaTarefasPorEmail(String token) {

        String email = jwtUtil.extrairEmailToken(token.substring(7));

        return tarefaConverter.paraListaTarefasDTORecord(tarefasRepository.findByEmailUsuario(email));

    }

    public void deletaTarefaPorId(String id) {

        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente " + id, e.getCause());
        }

    }

    public TarefasDTORecord alteraStatus(StatusNotificacaoEnum status, String id) {

        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não" +
                    "encontrada " + id));

            entity.setStatus(status);
            entity.setDataAlteração(LocalDateTime.now());


            return tarefaConverter.paraTarefasDTORecord(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }

    }

    public TarefasDTORecord updateTarefas(TarefasDTORecord dto, String id) {

        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não" +
                    "encontrada " + id));

            entity.setDataAlteração(LocalDateTime.now());

            tarefaUpdateConverter.updateTarefas(dto, entity); // transforma o objeto entity

            return tarefaConverter.paraTarefasDTORecord(tarefasRepository.save(entity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }


    }
}

