# Aplicação de Contabilidade

Esta aplicação é uma solução para gerenciar clientes, contadores, empresas e endereços relacionados a uma empresa de contabilidade. Ela oferece APIs para criar, editar, consultar e listar essas entidades, integrando-se a serviços externos para obter informações de endereço e verificar o status de contadores.

## Funcionalidades

### Contadores

- Criar, editar, consultar e listar contadores.
- Verificar se um contador está ativo usando integração com uma API externa.

### Clientes

- Criar, editar, consultar e listar clientes associados a um contador.
- Cada cliente possui um endereço.

### Empresas

- Criar, editar, consultar e listar empresas associadas a um cliente.
- Cada empresa possui um endereço.

### Endereços

- Criar e listar endereços de clientes e empresas.
- Os endereços são obtidos automaticamente usando a API ViaCEP.

## Tecnologias Utilizadas

- Spring Boot: Framework para desenvolvimento de aplicativos Java.
- Feign: Cliente HTTP para integração de serviços web.
- MySQL: Banco de dados relacional.
- Hibernate: Mapeamento objeto-relacional para persistência de dados.
- Lombok: Biblioteca para geração automática de código repetitivo.
- Maven: Gerenciador de dependências e construção de projetos.

## Estrutura do Projeto

- `src/main/java`: Contém os arquivos-fonte Java.
    - `controllers`: Controladores da API.
    - `models`: Entidades do banco de dados.
    - `repositories`: Repositórios para acesso aos dados.
    - `services`: Lógica de negócios e serviços.
    - `clients`: Clientes Feign para integração com serviços externos.
    - `exceptions`: Classes de exceção personalizadas.
- `src/main/resources`: Recursos estáticos, como arquivos de configuração.

## Configuração

Antes de iniciar a aplicação, configure as seguintes propriedades no arquivo `application.properties`:

- `spring.datasource.url`: URL do banco de dados PostgreSQL.
- `spring.datasource.username`: Nome de usuário do banco de dados.
- `spring.datasource.password`: Senha do banco de dados.
- `cfc.url`: URL do serviço CFC para verificação de contadores.
- `viacep.url`: URL do serviço ViaCEP para obtenção de endereços.

## Como Executar

1. Certifique-se de ter o Java e o PostgreSQL instalados em sua máquina.
2. Clone este repositório: `git clone https://github.com/seu-usuario/aplicacao-contabilidade.git`.
3. Navegue até o diretório do projeto: `cd aplicacao-contabilidade`.
4. Configure as propriedades do banco de dados e dos serviços externos em `src/main/resources/application.properties`.
5. Execute o projeto usando Maven: `mvn spring-boot:run`.

## Contribuição

Contribuições são bem-vindas! Se encontrar um problema ou quiser adicionar um novo recurso, sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo LICENSE para obter mais detalhes.