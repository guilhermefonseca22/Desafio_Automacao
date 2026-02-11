# Desafio_Automacao

# Automação de Testes - API ServeRest

Projeto de automação de testes da API  https://serverest.dev  
Desenvolvido com Java, RestAssured e JUnit 5.

# Tecnologias Utilizadas

- Java 17
- Maven 3.9+
- RestAssured
- JUnit 5
- GitHub Actions (CI)


# Configuração do Ambiente


# Pré-requisitos

* Instalar Java 17+
* Instalar Maven 3.8+


# Clonando o Projeto

git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio


# Instalando Dependências

mvn clean install

Esse comando:

* Compila o projeto
* Baixa dependências
* Executa todos os testes


# API Utilizada

Base URL:

https://serverest.dev


# Autenticação

A autenticação é feita via:

POST /login

O token é gerado automaticamente pelo `AuthManager` e utilizado nos testes protegidos.



# Estrutura do Projeto

src/test/java
│
├── gerencia_usuarios
│   └── AlterarUsuarioTest.java
└── BuscarUsuarioIdTest.java
└── CadastrarUsuariosTest.java
└── ExcluirUsuarioTest.java
└── ListarUsuariosTest.java
└── LoginTest.java
│
└── utils
    ├── AuthManager.java
    ├── BaseApiTest.java
    └── PayloadLoader.java
    └── ResourceReader.java
    └── SchemaLoader.java
    │
    └── resources
    └── payloads
    └── schemas


#  Cenários Automatizados

- Criar usuário
- Listar usuários
- Validar primeiro usuário
- Excluir usuário com sucesso
- Não excluir usuário inexistente


# Integração Contínua

O projeto possui pipeline configurada no GitHub Actions.

A cada push é executado:

* Build do projeto
* Execução automática dos testes

Arquivo da pipeline:


.github/workflows/ci.yml


# Relatórios

Após execução local, os relatórios ficam em:


target/surefire-reports



#  Autor

Guilherme Fonseca Costa



