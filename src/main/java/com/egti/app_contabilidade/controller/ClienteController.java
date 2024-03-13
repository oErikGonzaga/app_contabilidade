package com.egti.app_contabilidade.controller;

import com.egti.app_contabilidade.model.Cliente;
import com.egti.app_contabilidade.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("{idContador}/criar")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente criarCliente(@PathVariable UUID idContador,
                                @RequestBody Cliente cliente){
        return clienteService.criarCliente(cliente, idContador);
    }

    @PutMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente editarCliente(@PathVariable UUID id,
                                 @RequestParam(value = "nome", required = false) String nome,
                                 @RequestParam(value = "cpf", required = false) String cpf) {

        return clienteService.editarCliente(id, nome, cpf);
    }

    @GetMapping("{id}/consultar")
    @ResponseStatus(HttpStatus.OK)
    public Cliente consultarCliente(@PathVariable UUID id){
        return clienteService.consultarCliente(id);
    }

    @GetMapping("/do-contador/{idContador}")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> listarClientesDoContador(@PathVariable UUID idContador){
        return clienteService.listarClientesDoContador(idContador);
    }

}
