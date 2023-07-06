package io.github.ubistart.controller;

import io.github.ubistart.domain.enums.TarefaStatus;
import io.github.ubistart.dto.tarefa.TarefaAtualizarDto;
import io.github.ubistart.dto.tarefa.TarefaCadastraDto;
import io.github.ubistart.dto.tarefa.TarefaRetornoDto;
import io.github.ubistart.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tarefa")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class TarefaController {

    @Autowired
    TarefaService tarefaService;

    @PostMapping
    public ResponseEntity cadastrarTarefa(@Valid @RequestBody TarefaCadastraDto tarefaCadastraDto) {
        TarefaRetornoDto tarefa = tarefaService.salvarTarefa(tarefaCadastraDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);
    }

    @PatchMapping("/{idTarefa}")
    public ResponseEntity atualizaStatus(@PathVariable("idTarefa") Long idTarefa, @RequestParam("tarefaStatus") TarefaStatus tarefaStatus) {
        TarefaRetornoDto tarefa = tarefaService.concluirTarefa(idTarefa, tarefaStatus);
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }

    @PutMapping("/{idTarefa}")
    public ResponseEntity atualizaTarefa(@PathVariable("idTarefa") Long idTarefa, @RequestBody TarefaAtualizarDto tarefaAtualizarDto) {
        TarefaRetornoDto tarefa = tarefaService.atualizarTarefa(idTarefa, tarefaAtualizarDto);
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity buscaTarefasPorUsuario(@PathVariable("idUsuario") Long idUsuario) {
        ResponseEntity responseEntities = tarefaService.buscaTarefaPorUsuario(idUsuario);
        return responseEntities;
    }

    @GetMapping("/admin")
    public ResponseEntity buscaTodasTarefas(@PageableDefault(
            size = 10, page = 0, sort = "idTarefa", direction = Sort.Direction.ASC) Pageable pageable,
                                            @RequestParam(value = "tarefaAtrasada",defaultValue = "false") Boolean tarefaAtrasada ){
        ResponseEntity responseEntity = tarefaService.buscaTodasTarefas(pageable, tarefaAtrasada);
        return responseEntity;
    }

}
