package com.alexandre.agendadortarefas.infrastructure.business.mapper;

import com.alexandre.agendadortarefas.infrastructure.business.dto.TarefasDTO;
import com.alexandre.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefasEntity(TarefasDTO dto);

    TarefasDTO paraTarefasDTO(TarefasEntity dto);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> dtos);

    List<TarefasDTO>  paraListaTarefasDTO(List<TarefasEntity> entities);

}
