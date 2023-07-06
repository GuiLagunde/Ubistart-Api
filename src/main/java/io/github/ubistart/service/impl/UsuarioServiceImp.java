package io.github.ubistart.service.impl;

import io.github.ubistart.util.SenhaUtil;
import io.github.ubistart.domain.entity.Usuario;
import io.github.ubistart.domain.enums.UsuarioRole;
import io.github.ubistart.repository.UsuarioRepository;
import io.github.ubistart.dto.usuario.UsuarioDto;
import io.github.ubistart.exception.ExceptionCustomizada;
import io.github.ubistart.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDto salvarUsuario(UsuarioDto usuarioDto){
        this.validaUsuario(usuarioDto);
        String senhaCriptografada = SenhaUtil.gerarSenhaBCrypty(usuarioDto.getSenha());
        Usuario usuarioU = new Usuario().criaUsuarioParaSalvarOuAtualizar(null,
                senhaCriptografada,
                usuarioDto.getEmail(),
                UsuarioRole.USUARIO);
        Usuario usuarioUSalvo = this.usuarioRepository.save(usuarioU);
        UsuarioDto usuarioResposta = new UsuarioDto().converteUsuarioEmUsuarioDto(usuarioUSalvo);
        return usuarioResposta;
    }

    private void validaUsuario(UsuarioDto usuarioDto) {
        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(usuarioDto.getEmail());
        if(usuario.isPresent()){
            throw new ExceptionCustomizada("Usuario já cadastrado");
        }
    }

    @Override
    public Usuario findByEmail(String email){
       return usuarioRepository.findByEmail(email).orElse(null);
    }
}
