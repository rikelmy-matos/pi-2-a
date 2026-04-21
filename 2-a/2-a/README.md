# Agenda Telefonica — Projeto Integrador II-A

Aplicacao de gerenciamento de contatos telefonicos desenvolvida em **Java 17** com **Spring Boot 3**, **Spring Data JPA** e **PostgreSQL**. Executa via interface de linha de comando (CLI) com menu interativo e persistencia total dos dados.

---

## Tecnologias

| Camada          | Tecnologia                        |
|-----------------|-----------------------------------|
| Linguagem       | Java 17                           |
| Framework       | Spring Boot 3.2.5                 |
| Persistencia    | Spring Data JPA + Hibernate       |
| Banco de Dados  | PostgreSQL 16 (Docker image)      |
| Build           | Maven 3.9                         |
| Reducao boilerplate | Lombok                        |
| Containerizacao | Docker + Docker Compose           |

---

## Estrutura do Projeto

```
src/main/java/com/pi/agenda/
├── Application.java          # Ponto de entrada Spring Boot
├── model/
│   └── Contato.java          # Entidade JPA (tabela: contatos)
├── repository/
│   └── ContatoRepository.java # Interface Spring Data JPA
├── service/
│   └── AgendaTelefonica.java  # Logica de negocio (CRUD)
└── cli/
    └── AgendaMenu.java        # Menu interativo CLI (CommandLineRunner)
```

---

## Funcionalidades (CRUD)

| Opcao | Operacao | Descricao                              |
|-------|----------|----------------------------------------|
| 1     | Create   | Adicionar novo contato                 |
| 2     | Delete   | Remover contato pelo nome              |
| 3     | Read     | Buscar contato pelo nome               |
| 4     | Update   | Atualizar telefone e/ou e-mail         |
| 5     | Read All | Listar todos os contatos               |
| 6     | —        | Sair do sistema                        |

Cada contato possui: **Nome** (unico), **Telefone** e **E-mail**.

---

## Como Executar

### Pre-requisitos

- [Docker](https://docs.docker.com/get-docker/) instalado
- [Docker Compose](https://docs.docker.com/compose/) disponivel

### Subir tudo com Docker Compose

```bash
# Na pasta raiz do projeto (onde esta o docker-compose.yml)
docker compose up --build
```

O Docker Compose vai:
1. Baixar a imagem `postgres:16-alpine`
2. Criar o banco `agenda_db` com usuario/senha configurados
3. Fazer o build da aplicacao Java
4. Aguardar o PostgreSQL estar saudavel antes de iniciar o app
5. Abrir o menu interativo no terminal

> **Importante:** Como o app e interativo (CLI), execute com `stdin_open: true` e `tty: true` ja configurados. Para interagir, use:
> ```bash
> docker attach agenda_app
> ```

### Executar apenas o banco (desenvolvimento local)

```bash
docker compose up postgres -d
```

Em seguida, rode a aplicacao localmente:

```bash
./mvnw spring-boot:run
```

### Parar os containers

```bash
docker compose down
```

Para remover tambem o volume do banco:

```bash
docker compose down -v
```

---

## Variaveis de Ambiente

| Variavel   | Padrao       | Descricao              |
|------------|--------------|------------------------|
| `DB_HOST`  | `localhost`  | Host do PostgreSQL     |
| `DB_PORT`  | `5432`       | Porta do PostgreSQL    |
| `DB_NAME`  | `agenda_db`  | Nome do banco          |
| `DB_USER`  | `agenda_user`| Usuario do banco       |
| `DB_PASS`  | `agenda_pass`| Senha do banco         |

---

## Banco de Dados

A tabela `contatos` e criada automaticamente pelo Hibernate (`ddl-auto=update`) na primeira execucao:

```sql
CREATE TABLE contatos (
    id       BIGSERIAL PRIMARY KEY,
    nome     VARCHAR(150) NOT NULL UNIQUE,
    telefone VARCHAR(20)  NOT NULL,
    email    VARCHAR(200)
);
```

Para exportar o dump do banco:

```bash
docker exec agenda_postgres pg_dump -U agenda_user agenda_db > dump.sql
```

---

## Tratamento de Excecoes

- Campo obrigatorio vazio: exibe aviso e pede novamente
- Contato duplicado (mesmo nome): exibe mensagem de erro, nao interrompe o sistema
- Contato nao encontrado: exibe mensagem informativa

---

## Boas Praticas Aplicadas

- Separacao de camadas: **Model / Repository / Service / CLI**
- Uso de **DAO Pattern** via Spring Data JPA
- **Transacoes** declarativas com `@Transactional`
- **Logs** via SLF4J / Logback (`@Slf4j`)
- Variaveis de ambiente para configuracao sensivel
- Multi-stage Docker build (imagem final leve com JRE Alpine)

---

## Entrega Final

- [x] Codigo-fonte Java
- [x] Banco de dados PostgreSQL via Docker
- [ ] Dump do banco (`dump.sql`) — gerar apos execucao
- [ ] Video demonstrativo do CRUD completo

**Prazo:** 16/06/2026 ate 23:30

---

## Referencias

- Herbert Schildt — *Java: The Complete Reference*
- Kathy Sierra — *Use a Cabeca! Java*
- C. J. Date — *Introducao a Sistemas de Bancos de Dados*
- [Spring Boot Docs](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Data JPA Docs](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
