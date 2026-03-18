package com.alexandre.agendadortarefas.infrastructure.business.mapper;

import com.alexandre.agendadortarefas.infrastructure.business.dto.TarefasDTO;
import com.alexandre.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

// NullValuePropertyMappingStrategy.IGNORE -> caso um dos dois objetos tenha nulo, o que vale é o valor não nulo
@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {


    //@MappingTarget assinala que o objeto é o principal, caso o outro seja nulo
    void updateTarefas(TarefasDTO dto, @MappingTarget  TarefasEntity entity);
}
