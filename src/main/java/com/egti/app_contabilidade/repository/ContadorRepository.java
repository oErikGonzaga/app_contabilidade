package com.egti.app_contabilidade.repository;

import com.egti.app_contabilidade.model.Cliente;
import com.egti.app_contabilidade.model.Contador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContadorRepository extends JpaRepository<Contador, UUID> {

    boolean existsByCpf(String cpf);

    boolean existsByCrc(String crc);
}
