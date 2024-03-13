package com.egti.app_contabilidade.service;

import com.egti.app_contabilidade.model.Endereco;

import java.util.List;
import java.util.UUID;

/**
 * Interface que define operações para manipulação de endereços.
 */
public interface EnderecoService {

    Endereco criarEndereco(Endereco endereco, UUID id);

    Endereco editarEndereco(UUID id, String cep, String numero);

    List<Endereco> listarEnderecos();

    Endereco listarEnderecoPorCliente(UUID id);

    Endereco listarEnderecoPorEmpresa(UUID id);
}
