package com.egti.app_contabilidade.config;

import com.egti.app_contabilidade.exception.CadastroException;
import com.egti.app_contabilidade.exception.InativoException;
import com.egti.app_contabilidade.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerAspect {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<String> handle(NotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
    }

    @ExceptionHandler(CadastroException.class)
    public final ResponseEntity<String> handle(CadastroException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(InativoException.class)
    public ResponseEntity<String> handle(InativoException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}