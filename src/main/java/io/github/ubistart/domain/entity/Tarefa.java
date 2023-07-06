package io.github.ubistart.domain.entity;

import io.github.ubistart.domain.enums.TarefaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tarefa")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idTarefa;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "prazo_entrega")
    private LocalDate prazoEntrega;

    @Column(name = "data_finalizado")
    private LocalDateTime dataFinalizado;

    @Enumerated(EnumType.STRING)
    @Column(name = "tarefa_status", nullable = false)
    private TarefaStatus tarefaStatus;

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @ManyToOne
    private Usuario usuario;

    public Tarefa CriaTarefaParaSalvarAtualizar(Long idTarefa,
                                                String descricao,
                                                LocalDate prazoEntrega,
                                                LocalDateTime dataFinalizado,
                                                TarefaStatus tarefaStatus,
                                                Usuario usuario) {
        return Tarefa.builder()
                .idTarefa(idTarefa)
                .descricao(descricao)
                .prazoEntrega(prazoEntrega)
                .dataFinalizado(dataFinalizado)
                .tarefaStatus(tarefaStatus)
                .usuario(usuario)
                .build();

    }

}
