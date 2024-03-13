package com.egti.app_contabilidade.client;

import com.egti.app_contabilidade.response.EnderecoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "viacep", url = "https://viacep.com.br")
public interface ViaCepClient {

    @GetMapping("ws/{cep}/json/")
    Optional<EnderecoResponse> getAddressByCep(@PathVariable("cep") String cep);
}