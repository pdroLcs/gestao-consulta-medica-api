# Gestão de Consulta Médica API

API REST para gerenciamento de pacientes, médicos e consultas, com foco em organização de agenda médica e rastreabilidade de status de atendimento.

---

## Visão geral

Este projeto foi construído com **Spring Boot** e segue uma arquitetura em camadas (controller, service, repository), com persistência em **PostgreSQL** e versionamento de schema via **Flyway**.

A API permite:
- Cadastrar e consultar pacientes.
- Cadastrar e listar médicos.
- Agendar consultas.
- Listar consultas por médico e por paciente.
- Cancelar consultas.

## Stack utilizada

- **Java 21**
- **Spring Boot 3**
    - Spring Web
    - Spring Data JPA
    - Spring Validation
- **PostgreSQL**
- **Flyway** (migrações de banco)
- **Springdoc OpenAPI / Swagger UI**
- **Maven**
- **Docker**
- **Render** (deploy)

## Documentação da API (Swagger)

A documentação interativa está disponível em:

- **Produção (Render):** https://gestao-consulta-medica-api.onrender.com/swagger-ui/index.html
- **OpenAPI JSON (produção):** https://gestao-consulta-medica-api.onrender.com/v3/api-docs
- **Local:** `http://localhost:8080/swagger-ui/index.html`

## Base URL

- **Produção:** `https://gestao-consulta-medica-api.onrender.com`
- **Local:** `http://localhost:8080`

## Endpoints principais

### Pacientes
- `GET /pacientes` — lista todos os pacientes.
- `GET /pacientes/{id}` — busca paciente por ID.
- `POST /pacientes` — cadastra um novo paciente.

### Médicos
- `GET /medico` — lista todos os médicos.
- `POST /medico` — cadastra um novo médico.

### Consultas
- `GET /consultas` — lista todas as consultas.
- `GET /consultas/medico/{id}` — lista consultas por médico.
- `GET /consultas/paciente/{id}` — lista consultas por paciente.
- `POST /consultas` — agenda uma nova consulta.
- `PATCH /consultas/{id}/cancelar` — cancela uma consulta.

> Para detalhes de payload, campos obrigatórios e respostas, consulte o Swagger UI.

---

## Como executar localmente

### Pré-requisitos
- Java 21+
- Maven
- PostgreSQL

### 1) Clonar o repositório

Clonar o repositório
```bash
git clone https://github.com/pdroLcs/gestao-consulta-medica-api.git
cd gestao-consulta-medica-api
```

### 2) Configurar variáveis de ambiente

A aplicação usa as seguintes variáveis para conexão com banco:

- `DB_URL`
- `DB_USER`
- `DB_PASSWORD`

Exemplo:

```bash
export DB_URL='jdbc:postgresql://localhost:5432/gestao_consulta_medica'
export DB_USER='postgres'
export DB_PASSWORD='postgres'
```

### 3) Executar a aplicação

```bash
./mvnw spring-boot:run
```

---

## Executando com Docker

### Build da imagem

```bash
docker build -t gestao-consulta-medica-api .
```

### Run do container

```bash
docker run --rm -p 8080:8080 \
  -e DB_URL='jdbc:postgresql://host.docker.internal:5432/gestao_consulta_medica' \
  -e DB_USER='postgres' \
  -e DB_PASSWORD='postgres' \
  gestao-consulta-medica-api
```

---

## Testes

Para executar os testes automatizados:

```cmd
./mvnw test
```

---

## Deploy

A aplicação está publicada no Render:

- https://gestao-consulta-medica-api.onrender.com/

---

## Licença

Projeto com objetivo educacional e de portfólio.