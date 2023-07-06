package io.github.ubistart.dto.tarefa;

import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaCadastraDto {

    @NotNull(message = "descricao não pode ser nula")
    @NotEmpty(message = "descricao não pode ficar em branco")
    private String descricao;

    @NotNull(message = "prazoEntrega não pode ser nulo")
    private LocalDate prazoEntrega;

    @NotNull(message = "idUsuario não pode ser nulo")
    private Long idUsuario;

}
