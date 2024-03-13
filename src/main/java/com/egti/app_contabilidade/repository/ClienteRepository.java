package com.egti.app_contabilidade.repository;

import com.egti.app_contabilidade.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    List<Cliente> findAllClientsByContadorId(UUID id);

    boolean existsByCpf(String cpf);
}
