package com.egti.app_contabilidade.dto.request;

import java.io.Serializable;

public record ContadorRequestDTO(String nome, String cpf, String crc) implements Serializable {

}
