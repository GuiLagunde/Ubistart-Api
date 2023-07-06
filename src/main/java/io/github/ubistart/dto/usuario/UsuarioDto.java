package io.github.ubistart.dto.usuario;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.*;

import io.github.ubistart.domain.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDto {

    private Long idUsuario;

    @NotNull(message = "Senha não pode ser nula")
    @NotEmpty(message = "Senha não pode ficar em branco")
    private String senha;

    @NotNull(message = "Senha não pode ser nula")
    @NotEmpty(message = "Senha não pode ficar em branco")
    @Email(message = "email Invalido")
    private String email;

    public UsuarioDto converteUsuarioEmUsuarioDto(Usuario usuarioU){
        return UsuarioDto.builder()
                .idUsuario(usuarioU.getIdUsuario())
                .email(usuarioU.getEmail())
                .build();
    }

}
