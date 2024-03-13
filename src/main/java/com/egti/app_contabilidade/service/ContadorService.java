package com.egti.app_contabilidade.service;

import com.egti.app_contabilidade.model.Contador;

import java.util.List;
import java.util.UUID;

/**
 * Interface que define operações para manipulação de contadores.
 */
public interface ContadorService {

    Contador criarContador(Contador contador);

    Contador editarContador(UUID id, String nome, String cpf, String crc);

    Contador consultarContador(UUID id);

    List<Contador> listarContadores();
}
