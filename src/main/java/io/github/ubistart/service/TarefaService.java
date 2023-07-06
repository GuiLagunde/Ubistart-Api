package io.github.ubistart.service;

import io.github.ubistart.domain.enums.TarefaStatus;
import io.github.ubistart.dto.tarefa.TarefaAtualizarDto;
import io.github.ubistart.dto.tarefa.TarefaCadastraDto;
import io.github.ubistart.dto.tarefa.TarefaRetornoDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
public interface TarefaService {

    TarefaRetornoDto salvarTarefa(TarefaCadastraDto tarefaCadastraDto);
    TarefaRetornoDto concluirTarefa(Long idTarefa, TarefaStatus tarefaStatus);
    TarefaRetornoDto atualizarTarefa(Long idTarefa, TarefaAtualizarDto tarefaAtualizarDto);

    ResponseEntity buscaTarefaPorUsuario(Long idUsuario);

    ResponseEntity buscaTodasTarefas(Pageable pageable, Boolean tarefaAtrasada);


}
