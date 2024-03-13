package com.egti.app_contabilidade.service;

import com.egti.app_contabilidade.model.Cliente;

import java.util.List;
import java.util.UUID;

/**
 * Interface que define operações para manipulação de clientes.
 */
public interface ClienteService {

    Cliente criarCliente(Cliente cliente, UUID idContador);

    Cliente editarCliente(UUID id, String nome, String cpf);

    Cliente consultarCliente(UUID id);

    List<Cliente> listarClientesDoContador(UUID idContador);
}
