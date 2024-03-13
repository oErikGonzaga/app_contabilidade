package com.egti.app_contabilidade.repository;

import com.egti.app_contabilidade.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {

    Endereco findEnderecoByClienteId(UUID id);
    Endereco findEnderecoByEmpresaId(UUID id);

}
