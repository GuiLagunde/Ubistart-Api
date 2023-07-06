package io.github.ubistart.service.impl;

import io.github.ubistart.domain.entity.Usuario;
import io.github.ubistart.domain.enums.UsuarioRole;
import io.github.ubistart.repository.UsuarioRepository;
import io.github.ubistart.exception.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticaServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository repository;

    public UserDetails autenticar( Usuario usuar){
        UserDetails user = loadUserByUsername(usuar.getEmail());
        boolean senhasBatem = encoder.matches( usuar.getSenha(), user.getPassword() );

        if(senhasBatem){
            return user;
        }

        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuar = repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

        String[] roles = usuar.getUsuarioRole() == UsuarioRole.ADMIN ?
                new String[]{"ADMIN"} : new String[]{"USUARIO"};


        return User
                .builder()
                .username(usuar.getEmail())
                .password(usuar.getSenha())
                .roles(roles)
                .build();
    }

}
