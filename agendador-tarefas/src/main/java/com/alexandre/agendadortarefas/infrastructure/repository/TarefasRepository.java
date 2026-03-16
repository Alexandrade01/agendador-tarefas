package com.alexandre.agendadortarefas.infrastructure.repository;

import com.alexandre.agendadortarefas.infrastructure.business.dto.TarefasDTO;
import com.alexandre.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// <TarefasEntity,String>  <classe da tabela, ID>
@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

}
