package com.egti.app_contabilidade.controller;


import com.egti.app_contabilidade.Enums.TipoEmpresa;
import com.egti.app_contabilidade.model.Empresa;
import com.egti.app_contabilidade.service.EmpresaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class EmpresaController {

    private final EmpresaService empresaService;

    @PostMapping("{idCliente}/empresa")
    @ResponseStatus(HttpStatus.CREATED)
    public Empresa criarEmpresa(@PathVariable UUID idCliente,
                                @RequestBody Empresa empresa){
        return empresaService.criarEmpresa(empresa, idCliente);
    }

    @PutMapping("empresa/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Empresa editarEmpresa(@PathVariable UUID id,
                                 @RequestParam(value = "nomeEmpresarial", required = false) String nomeEmpresarial,
                                 @RequestParam(value = "cnpj", required = false) String cnpj,
                                 @RequestParam(value = "tipoEmpresa", required = false) TipoEmpresa tipoEmpresa) {

        return empresaService.editarEmpresa(id, nomeEmpresarial, cnpj, tipoEmpresa);
    }

    @GetMapping("/empresa/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Empresa consultarEmpresa(@PathVariable UUID id){
        return empresaService.consultarEmpresa(id);
    }

    @GetMapping("/cliente/{idCliente}/empresas")
    @ResponseStatus(HttpStatus.OK)
    public List<Empresa> listarEmpresasDoCliente(@PathVariable UUID idCliente) {
        return empresaService.listarEmpresas(idCliente);
    }
}
