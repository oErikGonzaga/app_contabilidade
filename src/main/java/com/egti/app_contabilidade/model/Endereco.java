package com.egti.app_contabilidade.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String logradouro;

    @Column(length = 8, nullable = false)
    private String cep;

    @Column
    private String numero;

    @Column(nullable = false)
    private String cidade;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private Empresa empresa;

}
