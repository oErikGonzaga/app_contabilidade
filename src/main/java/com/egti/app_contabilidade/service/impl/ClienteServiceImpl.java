package com.egti.app_contabilidade.service.impl;

import com.egti.app_contabilidade.exception.CadastroException;
import com.egti.app_contabilidade.exception.NotFoundException;
import com.egti.app_contabilidade.model.Cliente;
import com.egti.app_contabilidade.model.Contador;
import com.egti.app_contabilidade.repository.ClienteRepository;
import com.egti.app_contabilidade.service.ClienteService;
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
 * Serviço responsável por manipular operações relacionadas a clientes.
 */

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ContadorService contadorService;

    /**
     * Cria um novo cliente associado a um contador.
     *
     * @param cliente O objeto Cliente a ser criado.
     * @param idContador O identificador único do contador associado ao cliente.
     * @return O Cliente criado.
     * @throws CadastroException Se ocorrer um erro durante o cadastro do cliente.
     */
    @Override
    public Cliente criarCliente(Cliente cliente, UUID idContador) {

        Contador contador = contadorService.consultarContador(idContador);
        cliente.setContador(contador);

        if (Objects.nonNull(cliente.getId()))
            throw new CadastroException("Tentativa de cadastro incorreta");

        if (clienteRepository.existsByCpf(cliente.getCpf()))
            throw new CadastroException("Cliente já é cadastrado");

        return clienteRepository.save(cliente);
    }

    /**
     * Edita as informações de um cliente existente.
     *
     * @param id O identificador único do cliente a ser editado.
     * @param nome O novo nome do cliente (opcional).
     * @param cpf O novo CPF do cliente (opcional).
     * @return O Cliente após a edição.
     */
    @Override
    @Transactional
    public Cliente editarCliente(UUID id, String nome, String cpf) {

        var cliente = consultarCliente(id);

        if (isValueValid(nome, cliente.getNome()))
            cliente.setNome(nome);

        if (isValueValid(cpf, cliente.getCpf()))
            cliente.setCpf(cpf);

        return cliente;
    }

    /**
     * Consulta um cliente pelo seu identificador único.
     *
     * @param id O identificador único do cliente a ser consultado.
     * @return O Cliente encontrado.
     * @throws NotFoundException Se o cliente não for encontrado.
     */
    @Override
    public Cliente consultarCliente(UUID id) {
        return clienteRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    /**
     * Lista todos os clientes associados a um contador específico.
     *
     * @param idContador O identificador único do contador.
     * @return Uma lista de clientes associados ao contador.
     */
    @Override
    public List<Cliente> listarClientesDoContador(UUID idContador) {

        Optional<List<Cliente>> clientes = Optional.ofNullable(clienteRepository.findAllClientsByContadorId(idContador));

        return clientes.get();
    }
}
