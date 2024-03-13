package com.egti.app_contabilidade.model;

import com.egti.app_contabilidade.model.Contador;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 11, nullable = false)
    private String cpf;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "contador_id", referencedColumnName = "id")
    private Contador contador;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "cliente")
    private Endereco endereco;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
    private List<Empresa> empresa;

    @Column(nullable = false, columnDefinition = "BIT(1)")
    private Boolean ativo;

}
