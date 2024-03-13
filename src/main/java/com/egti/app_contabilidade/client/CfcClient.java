package com.egti.app_contabilidade.client;

import com.egti.app_contabilidade.model.Contador;
import com.egti.app_contabilidade.response.ContadorResponse;
import com.egti.app_contabilidade.response.EnderecoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "cfc", url = "https://sistemas.cfc.org.br/servico/api/Profissional")
public interface CfcClient {

    @GetMapping("?cpf={cpf}")
    Optional<ContadorResponse> getAtivoByCpf(@PathVariable("cpf") String cpf);
}

