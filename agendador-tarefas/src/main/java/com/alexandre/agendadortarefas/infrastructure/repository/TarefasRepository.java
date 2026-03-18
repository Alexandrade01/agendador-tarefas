package com.alexandre.agendadortarefas.infrastructure.repository;

import com.alexandre.agendadortarefas.infrastructure.business.dto.TarefasDTO;
import com.alexandre.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

// <TarefasEntity,String>  <classe da tabela, ID>
@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

    List<TarefasEntity> findByDataEventoBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);

    List<TarefasEntity> findByEmailUsuario(String email);
}
