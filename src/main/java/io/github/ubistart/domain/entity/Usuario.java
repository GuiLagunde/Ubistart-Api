package io.github.ubistart.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.ubistart.domain.enums.UsuarioRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@Where(clause = "deletado=false OR deletado IS NULL")
@Table(name = "usuario")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idUsuario;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "usuario_role", nullable = false)
    private UsuarioRole usuarioRole;

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @Transient
    public Usuario criaUsuarioParaSalvarOuAtualizar(Long idUsuario,
                                                    String senha,
                                                    String email,
                                                    UsuarioRole usuarioRole){
        return Usuario.builder()
                .idUsuario(idUsuario)
                .senha(senha)
                .email(email)
                .usuarioRole(usuarioRole)
                .build();

    }

}