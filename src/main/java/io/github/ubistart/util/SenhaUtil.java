package io.github.ubistart.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtil {
    public static String gerarSenhaBCrypty(String senha){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(senha);
    }
}
