package io.github.ubistart.dto.tarefa;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.ubistart.domain.entity.Tarefa;
import io.github.ubistart.domain.enums.TarefaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TarefaRetornoDto {

    private Long idTarefa;
    private String descricao;
    private LocalDate prazoEntrega;
    private LocalDateTime dataFinalizado;
    private TarefaStatus tarefaStatus;
    private Boolean tarefaAtrasada;
    private String email;

    public TarefaRetornoDto ConverteTarefaEmTarefaRetornoDto(Tarefa tarefa){
        Boolean tarefaEstaAtrasada = this.tarefaEstaAtrasada(tarefa);
        return TarefaRetornoDto.builder()
                .idTarefa(tarefa.getIdTarefa())
                .descricao(tarefa.getDescricao())
                .prazoEntrega(tarefa.getPrazoEntrega())
                .dataFinalizado(tarefa.getDataFinalizado())
                .tarefaStatus(tarefa.getTarefaStatus())
                .tarefaAtrasada(tarefaEstaAtrasada)
                .email(tarefa.getUsuario().getEmail())
                .build();
    }

    private Boolean tarefaEstaAtrasada(Tarefa tarefa){
        Boolean tarefaAtrasada = false;
        if(tarefa.getTarefaStatus() != TarefaStatus.CONCLUIDA && tarefa.getPrazoEntrega().isBefore(LocalDate.now())){
            tarefaAtrasada = true;
        }
        return tarefaAtrasada;
    }

}
