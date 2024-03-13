package com.egti.app_contabilidade.service;

import com.egti.app_contabilidade.Enums.TipoEmpresa;
import com.egti.app_contabilidade.model.Empresa;

import java.util.List;
import java.util.UUID;

/**
 * Interface que define operações para manipulação de empresas.
 */
public interface EmpresaService {

    Empresa criarEmpresa(Empresa empresa, UUID idCliente);

    Empresa editarEmpresa(UUID id, String nomeEmpresarial, String cnpj, TipoEmpresa tipoEmpresa);

    Empresa consultarEmpresa(UUID id);

    List<Empresa> listarEmpresas(UUID idCliente);


}
