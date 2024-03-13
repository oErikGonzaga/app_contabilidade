package com.egti.app_contabilidade.repository;

import com.egti.app_contabilidade.model.Cliente;
import com.egti.app_contabilidade.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, UUID> {

    List<Empresa> findAllEmpresaByClienteId (UUID id);

    boolean existsByCnpj(String cnpj);
}
