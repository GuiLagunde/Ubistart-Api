package io.github.ubistart.controller;

import io.github.ubistart.dto.usuario.UsuarioDto;
import io.github.ubistart.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity cadastrarUsuario(@Valid @RequestBody UsuarioDto usuarioDto){
        UsuarioDto usuario = usuarioService.salvarUsuario(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

}
