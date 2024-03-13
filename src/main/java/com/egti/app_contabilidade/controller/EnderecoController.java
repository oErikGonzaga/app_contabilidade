package com.egti.app_contabilidade.controller;

import com.egti.app_contabilidade.model.Endereco;
import com.egti.app_contabilidade.service.EnderecoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping("{id}/enderecos")
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco criarEndereco(@PathVariable UUID id,
                                  @RequestBody Endereco endereco){
        return enderecoService.criarEndereco(endereco, id);
    }

    @GetMapping("{id}/endereco")
    @ResponseStatus(HttpStatus.OK)
    public List<Endereco> listar(@PathVariable UUID id){
        return enderecoService.listarEnderecos();
    }

}
