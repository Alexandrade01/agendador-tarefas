package com.alexandre.agendadortarefas.infrastructure.business.mapper;

import com.alexandre.agendadortarefas.infrastructure.business.dto.TarefasDTORecord;
import com.alexandre.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    @Mapping(source = "id",target = "id")
    TarefasEntity paraTarefasEntity(TarefasDTORecord dto);

    TarefasDTORecord paraTarefasDTORecord(TarefasEntity dto);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTORecord> dtos);

    List<TarefasDTORecord>  paraListaTarefasDTORecord(List<TarefasEntity> entities);

}
