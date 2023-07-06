package io.github.ubistart.service;


import io.github.ubistart.domain.entity.Usuario;
import io.github.ubistart.dto.usuario.UsuarioDto;

public interface UsuarioService {

    UsuarioDto salvarUsuario(UsuarioDto usuarioDto);

    Usuario findByEmail(String email);
}
