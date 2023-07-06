package io.github.ubistart.controller;

import io.github.ubistart.domain.entity.Usuario;
import io.github.ubistart.domain.enums.UsuarioRole;
import io.github.ubistart.dto.CredenciaisDTO;
import io.github.ubistart.dto.TokenDTO;
import io.github.ubistart.exception.SenhaInvalidaException;
import io.github.ubistart.configura.jwt.JwtService;
import io.github.ubistart.service.UsuarioService;
import io.github.ubistart.service.impl.AutenticaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AutorizacaoController {

    private final AutenticaServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UsuarioService userService;

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try{
            Usuario usuar = Usuario.builder()
                    .email(credenciais.getLogin())
                    .senha(credenciais.getSenha())
                    .idUsuario(userService.findByEmail(credenciais.getLogin()).getIdUsuario())
                    .usuarioRole(userService.findByEmail(credenciais.getLogin()).getUsuarioRole()).build();

            UserDetails usuarioAutenticado = usuarioService.autenticar(usuar);
            String token = jwtService.gerarToken(usuar);
            return new TokenDTO(usuar.getEmail(), token,usuar.getIdUsuario().intValue(),usuar.getUsuarioRole().equals(UsuarioRole.ADMIN));
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
