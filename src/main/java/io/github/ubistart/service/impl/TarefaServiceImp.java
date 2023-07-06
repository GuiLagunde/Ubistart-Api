package io.github.ubistart.service.impl;

import io.github.ubistart.domain.entity.Tarefa;
import io.github.ubistart.domain.entity.Usuario;
import io.github.ubistart.domain.enums.TarefaStatus;
import io.github.ubistart.repository.TarefaRepository;
import io.github.ubistart.repository.UsuarioRepository;
import io.github.ubistart.dto.tarefa.TarefaAtualizarDto;
import io.github.ubistart.dto.tarefa.TarefaCadastraDto;
import io.github.ubistart.dto.tarefa.TarefaRetornoDto;
import io.github.ubistart.exception.ExceptionCustomizada;
import io.github.ubistart.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TarefaServiceImp implements TarefaService {


    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @Override
    public TarefaRetornoDto salvarTarefa(TarefaCadastraDto tarefaCadastraDto) {

        Usuario usuario = this.usuarioRepository.findById(tarefaCadastraDto.getIdUsuario())
                .orElseThrow(() -> new ExceptionCustomizada("Usuario não encontrado"));

        if(tarefaCadastraDto.getPrazoEntrega().isBefore(LocalDate.now())){
            throw new ExceptionCustomizada("Data de entrega não pode ser menor que há atual(hoje)");
        }

        Tarefa tarefa = new Tarefa().CriaTarefaParaSalvarAtualizar(null,
               tarefaCadastraDto.getDescricao(),
               tarefaCadastraDto.getPrazoEntrega(),
               null,
               TarefaStatus.PENDENTE,
                usuario);
        Tarefa tarefaSalva = this.tarefaRepository.save(tarefa);
        TarefaRetornoDto tarefaRetornoDto = new TarefaRetornoDto().ConverteTarefaEmTarefaRetornoDto(tarefaSalva);
        return tarefaRetornoDto;
    }

    @Transactional
    @Override
    public TarefaRetornoDto concluirTarefa(Long idTarefa, TarefaStatus tarefaStatus) {
        Tarefa tarefa = this.tarefaRepository.findById(idTarefa)
                .orElseThrow(() -> new ExceptionCustomizada("Tarefa não encontrado"));
        this.validaTarefa(tarefa);

        tarefa.setTarefaStatus(tarefaStatus);
        tarefa.setDataFinalizado(LocalDateTime.now());
        Tarefa tarefaSalva = this.tarefaRepository.save(tarefa);
        TarefaRetornoDto tarefaRetornoDto = new TarefaRetornoDto().ConverteTarefaEmTarefaRetornoDto(tarefaSalva);
        return tarefaRetornoDto;
    }

    @Transactional
    @Override
    public TarefaRetornoDto atualizarTarefa(Long idTarefa, TarefaAtualizarDto tarefaAtualizarDto) {
        Tarefa tarefa = this.tarefaRepository.findById(idTarefa).get();
        this.validaTarefa(tarefa);
        tarefa.setDescricao(tarefaAtualizarDto.getDescricao());
        tarefa.setPrazoEntrega(tarefaAtualizarDto.getPrazoEntrega());
        Tarefa tarefaSalva = this.tarefaRepository.save(tarefa);
        TarefaRetornoDto tarefaRetornoDto = new TarefaRetornoDto().ConverteTarefaEmTarefaRetornoDto(tarefaSalva);
        return tarefaRetornoDto;
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity buscaTarefaPorUsuario(Long idUsuario) {
        List<Tarefa> tarefas = this.tarefaRepository.findByUsuarioIdUsuario(idUsuario);
        if(tarefas.isEmpty()){
           return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else{
            List<TarefaRetornoDto> tarefaRetornoDtos = tarefas.stream()
                    .map(tarefa -> new TarefaRetornoDto().ConverteTarefaEmTarefaRetornoDto(tarefa))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(tarefaRetornoDtos);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity buscaTodasTarefas(Pageable pageable,Boolean tarefaAtrasada) {
        Page<Tarefa> tarefas;
        if(tarefaAtrasada){
            tarefas = this.tarefaRepository.findByPrazoEntregaBefore(pageable,LocalDate.now());
        }else{
            tarefas = this.tarefaRepository.findAll(pageable);
        }
        if(Objects.isNull(tarefas) || tarefas.getContent().isEmpty() ){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else{
            List<TarefaRetornoDto> tarefaRetornoDtos = tarefas.getContent().stream()
                    .map(tarefa -> new TarefaRetornoDto().ConverteTarefaEmTarefaRetornoDto(tarefa))
                    .collect(Collectors.toList());
            Page<TarefaRetornoDto> retornoDtoPage = new PageImpl<>(tarefaRetornoDtos, pageable, tarefas.getTotalElements());
            return ResponseEntity.status(HttpStatus.OK).body(retornoDtoPage);
        }
    }

    private void validaTarefa(Tarefa tarefa){
        if(tarefa.getTarefaStatus() == TarefaStatus.CONCLUIDA){
            throw new ExceptionCustomizada("tarefa já esta concluida e não pode ser alterada");
        }
    }

}
