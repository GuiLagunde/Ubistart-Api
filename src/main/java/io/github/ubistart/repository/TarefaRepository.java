package io.github.ubistart.repository;

import io.github.ubistart.domain.entity.Tarefa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByUsuarioIdUsuario(Long idUsuario);

    Page<Tarefa> findAll(Pageable pageable);

    Page<Tarefa> findByPrazoEntregaBefore(Pageable pageable, LocalDate prazoEntrega);

}
