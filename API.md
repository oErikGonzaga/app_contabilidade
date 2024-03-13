# Documentação da API

A seguir, são listados os endpoints disponíveis na API de Contabilidade.

## Contadores

### Criar Contador

- Método: POST
- Endpoint: `/api/contador`
- Descrição: Cria um novo contador.
- Corpo da Requisição: Objeto JSON contendo os detalhes do contador (nome, CPF e CRC).

### Editar Contador

- Método: PUT
- Endpoint: `/api/contador/{id}`
- Descrição: Edita os detalhes de um contador existente.
- Parâmetros de Path:
    - `id`: UUID do contador a ser editado.
- Parâmetros de Consulta (Opcionais):
    - `nome`: Novo nome do contador.
    - `cpf`: Novo CPF do contador.
    - `crc`: Novo CRC do contador.

### Consultar Contador

- Método: GET
- Endpoint: `/api/contador/{id}`
- Descrição: Retorna os detalhes de um contador específico.
- Parâmetros de Path:
    - `id`: UUID do contador a ser consultado.

### Listar Contadores

- Método: GET
- Endpoint: `/api/contador`
- Descrição: Lista todos os contadores cadastrados.

## Clientes

### Criar Cliente

- Método: POST
- Endpoint: `/api/{idContador}/cliente`
- Descrição: Cria um novo cliente associado a um contador.
- Parâmetros de Path:
    - `idContador`: UUID do contador ao qual o cliente será associado.
- Corpo da Requisição: Objeto JSON contendo os detalhes do cliente (nome e CPF).

### Editar Cliente

- Método: PUT
- Endpoint: `/api/cliente/{id}`
- Descrição: Edita os detalhes de um cliente existente.
- Parâmetros de Path:
    - `id`: UUID do cliente a ser editado.
- Parâmetros de Consulta (Opcionais):
    - `nome`: Novo nome do cliente.
    - `cpf`: Novo CPF do cliente.

### Consultar Cliente

- Método: GET
- Endpoint: `/api/cliente/{id}`
- Descrição: Retorna os detalhes de um cliente específico.
- Parâmetros de Path:
    - `id`: UUID do cliente a ser consultado.

### Listar Clientes do Contador

- Método: GET
- Endpoint: `/api/contador/{idContador}/clientes`
- Descrição: Lista todos os clientes associados a um contador.
- Parâmetros de Path:
    - `idContador`: UUID do contador.

## Empresas

### Criar Empresa

- Método: POST
- Endpoint: `/api/{idCliente}/empresa`
- Descrição: Cria uma nova empresa associada a um cliente.
- Parâmetros de Path:
    - `idCliente`: UUID do cliente ao qual a empresa será associada.
- Corpo da Requisição: Objeto JSON contendo os detalhes da empresa (CNPJ, nome empresarial e tipo).

### Editar Empresa

- Método: PUT
- Endpoint: `/api/empresa/{id}`
- Descrição: Edita os detalhes de uma empresa existente.
- Parâmetros de Path:
    - `id`: UUID da empresa a ser editada.
- Parâmetros de Consulta (Opcionais):
    - `nomeEmpresarial`: Novo nome empresarial da empresa.
    - `cnpj`: Novo CNPJ da empresa.
    - `tipoEmpresa`: Novo tipo da empresa.

### Consultar Empresa

- Método: GET
- Endpoint: `/api/empresa/{id}`
- Descrição: Retorna os detalhes de uma empresa específica.
- Parâmetros de Path:
    - `id`: UUID da empresa a ser consultada.

### Listar Empresas do Cliente

- Método: GET
- Endpoint: `/api/cliente/{idCliente}/empresas`
- Descrição: Lista todas as empresas associadas a um cliente.
- Parâmetros de Path:
    - `idCliente`: UUID do cliente.

## Endereços

### Criar Endereço

- Método: POST
- Endpoint: `/api/{id}/enderecos`
- Descrição: Cria um novo endereço associado a um cliente ou empresa.
- Parâmetros de Path:
    - `id`: UUID do cliente ou empresa ao qual o endereço será associado.
- Corpo da Requisição: Objeto JSON contendo os detalhes do endereço (logradouro, CEP, número e cidade).

### Listar Endereço do Cliente

- Método: GET
- Endpoint: `/api/{id}/endereco`
- Descrição: Retorna o endereço associado a um cliente específico.
- Parâmetros de Path:
    - `id`: UUID do cliente.

## Status

### Verificar Status da Aplicação

- Método: GET
- Endpoint: `/api/check`
- Descrição: Verifica o status da aplicação.

