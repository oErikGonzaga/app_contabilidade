package com.egti.app_contabilidade.service.impl;

import com.egti.app_contabilidade.Enums.TipoEmpresa;
import com.egti.app_contabilidade.exception.CadastroException;
import com.egti.app_contabilidade.exception.NotFoundException;
import com.egti.app_contabilidade.model.Cliente;
import com.egti.app_contabilidade.model.Empresa;
import com.egti.app_contabilidade.repository.EmpresaRepository;
import com.egti.app_contabilidade.service.ClienteService;
import com.egti.app_contabilidade.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.egti.app_contabilidade.util.ValidationUtil.isValueValid;

/**
 * Implementação dos serviços relacionados a empresas.
 */
@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final ClienteService clienteService;

    /**
     * Cria uma nova empresa associada a um cliente.
     *
     * @param empresa A empresa a ser criada.
     * @param idCliente O identificador único do cliente associado à empresa.
     * @return A Empresa criada.
     * @throws CadastroException Se ocorrer um erro durante o cadastro da empresa.
     */
    @Override
    public Empresa criarEmpresa(Empresa empresa, UUID idCliente) {

        Cliente cliente = clienteService.consultarCliente(idCliente);
        empresa.setCliente(cliente);

        if (Objects.nonNull(empresa.getId()))
            throw new CadastroException("Tentativa de cadastro incorreta");

        if (empresaRepository.existsByCnpj(empresa.getCnpj()))
            throw new CadastroException("Empresa já e cadastrada");


        return empresaRepository.save(empresa);
    }

    /**
     * Edita as informações de uma empresa existente.
     *
     * @param id O identificador único da empresa a ser editada.
     * @param nomeEmpresarial O novo nome empresarial da empresa (opcional).
     * @param cnpj O novo CNPJ da empresa (opcional).
     * @param tipoEmpresa O novo tipo de empresa (opcional).
     * @return A Empresa após a edição.
     */
    @Override
    @Transactional
    public Empresa editarEmpresa(UUID id, String nomeEmpresarial, String cnpj, TipoEmpresa tipoEmpresa) {

        var empresa = consultarEmpresa(id);

        if (isValueValid(nomeEmpresarial, empresa.getNomeEmpresarial()))
            empresa.setNomeEmpresarial(nomeEmpresarial);

        if (isValueValid(cnpj, empresa.getCnpj()))
            empresa.setCnpj(cnpj);

        if (isValueValid(tipoEmpresa, empresa.getTipoEmpresa()))
            empresa.setTipoEmpresa(tipoEmpresa);

        return empresa;
    }

    /**
     * Consulta uma empresa pelo seu identificador único.
     *
     * @param id O identificador único da empresa a ser consultada.
     * @return A Empresa encontrada.
     * @throws NotFoundException Se a empresa não for encontrada.
     */
    @Override
    public Empresa consultarEmpresa(UUID id) {
        return empresaRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    /**
     * Lista todas as empresas associadas a um cliente específico.
     *
     * @param idCliente O identificador único do cliente.
     * @return Uma lista de empresas associadas ao cliente.
     */
    @Override
    public List<Empresa> listarEmpresas(UUID idCliente) {
        Optional<List<Empresa>> empresas = Optional.ofNullable(empresaRepository.findAllEmpresaByClienteId(idCliente));

        return empresas.get();
    }
}
