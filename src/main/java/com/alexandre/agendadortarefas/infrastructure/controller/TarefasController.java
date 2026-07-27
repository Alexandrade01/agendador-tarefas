package com.alexandre.agendadortarefas.infrastructure.controller;

import com.alexandre.agendadortarefas.infrastructure.business.dto.TarefasDTO;
import com.alexandre.agendadortarefas.infrastructure.business.dto.TarefasDTORecord;
import com.alexandre.agendadortarefas.infrastructure.business.service.TarefasService;
import com.alexandre.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTORecord> gravarTarefa(@RequestBody TarefasDTORecord dto,
                                                   @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));

    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTORecord>> buscaListaDeTarefasPorPeriodo(

            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal
    ) {
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping("/todosEventos")
    public ResponseEntity<List<TarefasDTORecord>> buscaTarefasPorEmail(@RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<String> deletaTarefaPorId(@RequestParam("id") String id) {

        tarefasService.deletaTarefaPorId(id);

        return ResponseEntity.ok(String.format("Tarefa com id %s deletada !", id));

    }

    @PatchMapping
    public ResponseEntity<TarefasDTORecord> alteraStatusNotificacao(@RequestParam("status")StatusNotificacaoEnum status,
                                                              @RequestParam("id") String id){

        return ResponseEntity.ok(tarefasService.alteraStatus(status,id));
    }

    @PutMapping
    public ResponseEntity<TarefasDTORecord> updateTarefas(@RequestBody TarefasDTORecord dto, @RequestParam("id") String id){

        return ResponseEntity.ok(tarefasService.updateTarefas(dto,id));

    }


}
