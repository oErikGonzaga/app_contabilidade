package com.egti.app_contabilidade.service.impl;

import com.egti.app_contabilidade.client.ViaCepClient;
import com.egti.app_contabilidade.exception.CadastroException;
import com.egti.app_contabilidade.exception.NotFoundException;
import com.egti.app_contabilidade.model.Endereco;
import com.egti.app_contabilidade.repository.ClienteRepository;
import com.egti.app_contabilidade.repository.EnderecoRepository;
import com.egti.app_contabilidade.service.EnderecoService;
import feign.FeignException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


/**
 * Implementação dos serviços relacionados a endereços.
 */
@Service
@RequiredArgsConstructor
public class EndereçoServiceImpl implements EnderecoService {


    private final ViaCepClient viaCepClient;
    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;

    /**
     * Cria um novo endereço associado a um cliente.
     *
     * @param endereco O endereço a ser criado.
     * @param idCliente O identificador único do cliente associado ao endereço.
     * @return O Endereço criado.
     * @throws CadastroException Se ocorrer um erro durante o cadastro do endereço.
     */
    @Override
    @Transactional
    public Endereco criarEndereco(Endereco endereco, UUID idCliente) {

        try {
            var enderecoViaCep = viaCepClient.getAddressByCep(endereco.getCep()).
                    orElseThrow(() -> new CadastroException("Endereço não encontrado"));

            if (enderecoViaCep.isErro())
                throw new CadastroException("Endereço não encontrado");

            var cliente = clienteRepository.findById(idCliente).orElseThrow(NotFoundException::new);
            endereco.setCliente(cliente);

            if (StringUtils.isBlank(endereco.getLogradouro()))
                endereco.setLogradouro(enderecoViaCep.getLogradouro());

            if (StringUtils.isBlank(endereco.getCidade()))
                endereco.setCidade(enderecoViaCep.getLocalidade());

        } catch (FeignException e){
            throw new CadastroException("Endereço não encontrado");
        }

        return enderecoRepository.save(endereco);
    }

    /**
     * Edita as informações de um endereço existente.
     *
     * @param id O identificador único do endereço a ser editado.
     * @param cep O novo CEP do endereço (opcional).
     * @param numero O novo número do endereço (opcional).
     * @return O Endereço após a edição.
     */
    @Override
    public Endereco editarEndereco(UUID id, String cep, String numero) {
        return null;
    }


    /**
     * Lista todos os endereços.
     *
     * @return Uma lista de todos os endereços.
     */
    @Override
    public List<Endereco> listarEnderecos() {
        return enderecoRepository.findAll();
    }

    /**
     * Lista o endereço associado a um cliente específico.
     *
     * @param id O identificador único do cliente.
     * @return O Endereço associado ao cliente.
     */
    @Override
    public Endereco listarEnderecoPorCliente(UUID id) {
        return enderecoRepository.findEnderecoByClienteId(id);
    }

    /**
     * Lista o endereço associado a uma empresa específica.
     *
     * @param id O identificador único da empresa.
     * @return O Endereço associado à empresa.
     */
    @Override
    public Endereco listarEnderecoPorEmpresa(UUID id) {
        return enderecoRepository.findEnderecoByEmpresaId(id);
    }
}
