package com.egti.app_contabilidade.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "contadores")
public class Contador {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String crc;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contador")
    private List<Cliente> clientes;

    @Column(nullable = false, columnDefinition = "BIT(1)")
    private Boolean ativo;

}