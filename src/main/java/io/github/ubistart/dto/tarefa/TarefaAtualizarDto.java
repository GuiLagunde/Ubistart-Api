package io.github.ubistart.dto.tarefa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaAtualizarDto {

    @NotNull(message = "descricao não pode ser nula")
    @NotEmpty(message = "descricao não pode ficar em branco")
    private String descricao;

    @NotNull(message = "prazoEntrega não pode ser nulo")
    private LocalDate prazoEntrega;

}
