package com.egti.app_contabilidade.service.impl;

import com.egti.app_contabilidade.client.CfcClient;
import com.egti.app_contabilidade.exception.CadastroException;
import com.egti.app_contabilidade.exception.CfcApiException;
import com.egti.app_contabilidade.exception.InativoException;
import com.egti.app_contabilidade.exception.NotFoundException;
import com.egti.app_contabilidade.model.Contador;
import com.egti.app_contabilidade.repository.ContadorRepository;
import com.egti.app_contabilidade.response.ContadorResponse;
import com.egti.app_contabilidade.service.ContadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.egti.app_contabilidade.util.ValidationUtil.isValueValid;

/**
 * Implementação dos serviços relacionados a contadores.
 */
@Service
@RequiredArgsConstructor
public class ContadorServiceImpl implements ContadorService {

    private final ContadorRepository contadorRepository;
    private final CfcClient cfcClient;

    /**
     * Cria um novo contador.
     *
     * @param contador O contador a ser criado.
     * @return O Contador criado.
     * @throws InativoException Se o contador estiver inativo.
     * @throws CfcApiException Se ocorrer um erro na API do CFC.
     * @throws CadastroException Se ocorrer um erro durante o cadastro do contador.
     */
    @Override
    public Contador criarContador(Contador contador) {

        Optional<ContadorResponse> response = cfcClient.getAtivoByCpf(contador.getCpf());

        if (response.isPresent()) {
            if (!response.get().isPossuiCnaiAtivo()) {
                throw new InativoException("Contador está inativo. Não é possivel cadastrar");
            } else {
                throw new CfcApiException("Erro ao consultar a API do CFC");
            }
        }

        if (Objects.nonNull(contador.getId()))
            throw new CadastroException("Tentativa de cadastro incorreta");

        if (contadorRepository.existsByCpf(contador.getCpf()))
            throw new CadastroException("Contador já é Cadastrado");

        if (contadorRepository.existsByCrc(contador.getCrc()))
            throw new CadastroException("Contador já é Cadastrado");

        return contadorRepository.save(contador);
    }

    /**
     * Edita as informações de um contador existente.
     *
     * @param id O identificador único do contador a ser editado.
     * @param nome O novo nome do contador (opcional).
     * @param cpf O novo CPF do contador (opcional).
     * @param crc O novo CRC do contador (opcional).
     * @return O Contador após a edição.
     */
    @Override
    @Transactional
    public Contador editarContador(UUID id, String nome, String cpf, String crc){

        var contador = consultarContador(id);

        if (isValueValid(nome, contador.getNome()))
            contador.setNome(nome);

        if (isValueValid(cpf, contador.getCpf()))
            contador.setCpf(cpf);

        if (isValueValid(crc, contador.getCrc()))
            contador.setCrc(crc);

        return contador;
    }

    /**
     * Consulta um contador pelo seu identificador único.
     *
     * @param id O identificador único do contador a ser consultado.
     * @return O Contador encontrado.
     * @throws NotFoundException Se o contador não for encontrado.
     */
    @Override
    public Contador consultarContador(UUID id) {
        return contadorRepository.findById(id).orElseThrow(NotFoundException::new);
    }


    /**
     * Lista todos os contadores.
     *
     * @return Uma lista de todos os contadores.
     */
    @Override
    public List<Contador> listarContadores() {
        return contadorRepository.findAll();
    }
}
