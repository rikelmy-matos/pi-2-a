# Agenda Telefonica — Projeto Integrador II-A

Aplicacao de gerenciamento de contatos telefonicos desenvolvida em **Java 17** com **Spring Boot 3**, **Spring Data JPA** e **PostgreSQL**. Executa via interface de linha de comando (CLI) com menu interativo e persistencia total dos dados.

---

## Tecnologias

| Camada              | Tecnologia                   |
|---------------------|------------------------------|
| Linguagem           | Java 17                      |
| Framework           | Spring Boot 3.2.5            |
| Persistencia        | Spring Data JPA + Hibernate  |
| Banco de Dados      | PostgreSQL 16 (Docker image) |
| Build               | Maven 3.9                    |
| Reducao boilerplate | Lombok                       |
| Containerizacao     | Docker + Docker Compose      |

---

## Estrutura do Projeto

```
2-a/2-a/
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── src/main/
    ├── java/com/pi/agenda/
    │   ├── Application.java              # Ponto de entrada Spring Boot
    │   ├── model/
    │   │   └── Contato.java              # Entidade JPA (tabela: contatos)
    │   ├── repository/
    │   │   └── ContatoRepository.java    # Interface Spring Data JPA
    │   ├── service/
    │   │   └── AgendaTelefonica.java     # Logica de negocio (CRUD)
    │   └── cli/
    │       └── AgendaMenu.java           # Menu interativo CLI (CommandLineRunner)
    └── resources/
        └── application.properties        # Configuracao do banco e JPA
```

---

## Funcionalidades (CRUD)

| Opcao | Operacao | Descricao                      |
|-------|----------|--------------------------------|
| 1     | Create   | Adicionar novo contato         |
| 2     | Delete   | Remover contato pelo nome      |
| 3     | Read     | Buscar contato pelo nome       |
| 4     | Update   | Atualizar telefone e/ou e-mail |
| 5     | Read All | Listar todos os contatos       |
| 6     | —        | Sair do sistema                |

Cada contato possui: **Nome** (unico, obrigatorio), **Telefone** (obrigatorio) e **E-mail** (opcional).

---

## Como Executar

### Pre-requisitos

- [Docker](https://docs.docker.com/get-docker/) instalado
- [Docker Compose](https://docs.docker.com/compose/) disponivel

> **Todos os comandos abaixo devem ser executados dentro da pasta `2-a/2-a/`**, onde estao o `Dockerfile` e o `docker-compose.yml`.

```bash
cd 2-a/2-a
```

---

### Opcao 1 — Tudo via Docker (recomendado)

**Passo 1:** Sobe o PostgreSQL em background e faz o build da imagem:

```bash
docker compose up postgres -d
docker compose build app
```

**Passo 2:** Roda o app de forma interativa (stdin conectado ao seu terminal):

```bash
docker compose run --rm app
```

> `--rm` remove o container automaticamente ao sair (opcao 6 do menu).

O app vai:
1. Conectar ao PostgreSQL (aguarda o healthcheck passar)
2. Criar a tabela `contatos` automaticamente via Hibernate
3. Exibir o menu interativo no terminal

---

### Opcao 2 — Banco no Docker, app local (desenvolvimento)

```bash
# Dentro de 2-a/2-a/
docker compose up postgres -d
./mvnw spring-boot:run
```

---

### Parar e limpar

```bash
# Para o banco
docker compose down

# Para o banco e apaga os dados do volume
docker compose down -v
```

---

## Variaveis de Ambiente

| Variavel  | Padrao        | Descricao           |
|-----------|---------------|---------------------|
| `DB_HOST` | `localhost`   | Host do PostgreSQL  |
| `DB_PORT` | `5432`        | Porta do PostgreSQL |
| `DB_NAME` | `agenda_db`   | Nome do banco       |
| `DB_USER` | `agenda_user` | Usuario do banco    |
| `DB_PASS` | `agenda_pass` | Senha do banco      |

---

## Banco de Dados

A tabela e criada automaticamente pelo Hibernate (`ddl-auto=update`) na primeira execucao:

```sql
CREATE TABLE contatos (
    id       BIGSERIAL    PRIMARY KEY,
    nome     VARCHAR(150) NOT NULL UNIQUE,
    telefone VARCHAR(20)  NOT NULL,
    email    VARCHAR(200)
);
```

Para exportar o dump do banco (entregavel), dentro de `2-a/2-a/`:

```bash
docker exec agenda_postgres pg_dump -U agenda_user agenda_db > dump.sql
```

---

## Entrega Final

- [x] Codigo-fonte Java
- [x] Banco de dados PostgreSQL via Docker
- [ ] Dump do banco (`dump.sql`) — gerar com o comando acima apos execucao
- [ ] Video demonstrativo do CRUD completo

**Prazo:** 16/06/2026 ate 23:30
