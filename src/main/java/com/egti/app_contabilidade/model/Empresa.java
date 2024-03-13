package com.egti.app_contabilidade.model;

import com.egti.app_contabilidade.Enums.TipoEmpresa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String cnpj;

    @Column
    private String nomeEmpresarial;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoEmpresa tipoEmpresa;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "empresa")
    private Endereco endereco;

    @Column(nullable = false, columnDefinition = "BIT(1)")
    private Boolean ativa;

}
