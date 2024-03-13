package com.egti.app_contabilidade.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class EnderecoResponse implements Serializable {

    private String cep;
    private String localidade;
    private String logradouro;
    private boolean erro;

}
