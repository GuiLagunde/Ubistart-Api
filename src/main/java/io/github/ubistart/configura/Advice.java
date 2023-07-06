package io.github.ubistart.configura;


import io.github.ubistart.exception.ApiErros;
import io.github.ubistart.exception.ExceptionCustomizada;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class Advice {

    @ExceptionHandler(ExceptionCustomizada.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handlerApiErros(ExceptionCustomizada ex){
        String mensagemErro = ex.getMessage();
        return new ApiErros(mensagemErro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleMethodNotValidException(MethodArgumentNotValidException ex ){
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErros(errors);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleUsernameNotFoundException(UsernameNotFoundException ex ){
        String mensagemErro = ex.getMessage();
        return new ApiErros(mensagemErro);
    }
}
