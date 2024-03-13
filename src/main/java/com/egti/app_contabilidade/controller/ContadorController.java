package com.egti.app_contabilidade.controller;


import com.egti.app_contabilidade.model.Contador;
import com.egti.app_contabilidade.service.ContadorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class ContadorController {

    private final ContadorService contadorService;

    @GetMapping("/check")
    public ResponseEntity<String> checkStatus(){
        return ResponseEntity.ok("App Contabilidade está OK");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contador criarContador(@RequestBody Contador contador){
        return contadorService.criarContador(contador);
    }

    @PutMapping("/contador/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Contador editarContador(@PathVariable UUID id,
                                    @RequestParam(value = "nome", required = false) String nome,
                                    @RequestParam(value = "cpf", required = false) String cpf,
                                    @RequestParam(value = "crc", required = false) String crc){

        return contadorService.editarContador(id, nome, cpf, crc);
    }

    @GetMapping("/contador/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Contador consultarContador(@PathVariable UUID id){
        return contadorService.consultarContador(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Contador> listarContadoresComClientes(){
        return contadorService.listarContadores();
    }

}
