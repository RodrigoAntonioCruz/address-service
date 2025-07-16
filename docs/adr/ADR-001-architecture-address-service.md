
# ADR-001 - Arquitetura do Address Service

**Data:** 11/07/2025  
**Status:** Ativa  

## Contexto

O **Address Service** é um microserviço que integra múltiplos sistemas para busca e armazenamento de endereços brasileiros a partir do CEP (Código de Endereçamento Postal). Seu objetivo é reduzir latência de acesso aos dados, garantir resiliência contra falhas externas e prover flexibilidade no domínio de endereços.  

Para atender esses requisitos, o serviço utiliza arquitetura modular seguindo **Clean Architecture**, separando claramente domínio, casos de uso (use cases) e infraestrutura.  

## Decisão

### Tecnologias

- **Java 17** — Linguagem padrão para o serviço.
- **Spring Boot 3.0.2** — Framework principal para acelerar o desenvolvimento.
- **Spring Security** — Autenticação e controle de acesso.
- **Spring Cloud OpenFeign** — Cliente HTTP declarativo usado para integrar com serviços externos (ViaCEP).
- **Resilience4j** — Circuit Breaker e Retry para tornar as chamadas externas mais resilientes.
- **Spring Data Redis** — Para cache de dados de endereços.
- **Spring Data MongoDB** — Banco NoSQL para persistência dos dados de endereço.
- **Jackson** — Serialização/deserialização de JSON.
- **Lombok** — Redução de verbosidade do código Java.
- **Swagger/OpenAPI** — Documentação e testes da API REST.
- **Prometheus & Grafana** — Observabilidade e métricas.
- **Loki & Grafana** — Centralização e visualização de logs.
- **JUnit & Mockito** — Testes unitários, integrados e mocks.

### Estrutura do Projeto

O projeto é estruturado em múltiplos módulos (multi-module Maven project):

- `core/domain` — Entidades e objetos de valor (VOs).
- `core/use-case` — Casos de uso e lógica de negócio.
- `adapter/input` — Controllers e interfaces REST.
- `adapter/output` — Integrações externas (APIs, persistência, segurança).
- `app` — Configurações do Spring Boot.

Essa separação permite:

- Evolução isolada de regras de negócio.
- Independência de tecnologias/frameworks nas camadas externas.
- Facilidade para testes unitários e integração.
- Redução de acoplamento entre as partes do sistema.

### Funcionalidade Principal — Busca de Endereço

O core do serviço é a funcionalidade de busca de endereço por CEP, implementada via Use Case `FindAddressByCepUseCase`.  

**Fluxo resumido:**

- O CEP é encapsulado no VO `Cep`, que:
  - Valida formato (somente dígitos, tamanho 8, regex etc.)
  - Implementa o algoritmo de fallback, gerando variações do CEP com dígitos à direita zerados (e.g. `06233100` → `06233100`, `06233100` → `06233100` → `06233000` → etc.).
- O caso de uso consulta a infraestrutura através da porta de saída (`FindAddressByCepOutputPort`) que:
  - Verifica o cache Redis.
  - Se não encontrado, faz chamada HTTP ao ViaCEP.
- Caso nenhum endereço seja encontrado, dispara exceção `NotFoundException`.
- Se encontrar endereço através de um CEP fallback, cria um alias no cache Redis para o CEP original, apontando para o resultado encontrado, para otimizar consultas futuras.

#### Exemplo de Fallback

- Usuário consulta `06233123`.
- ViaCEP não encontra o endereço.
- O sistema tenta `06233120`.
- Não encontrou? Tenta `06233100`.
- Continua reduzindo até encontrar ou desistir.

### Testes e Qualidade

- Cobertura com **JaCoCo**.
- Regra mínima de cobertura de linhas (p.ex. 90%).
- Linters: Checkstyle configurado.
- Pipelines no GitHub Actions (ou GitLab CI/CD, conforme ambiente).

## Consequências

- **Performance**: Uso de Redis reduz a latência e alivia a carga sobre serviços externos.
- **Resiliência**: Circuit Breaker, Retry e fallback de CEP garantem maior disponibilidade.
- **Evolução**: Clean Architecture facilita mudanças sem impacto em toda a aplicação.
- **Observabilidade**: Stack completa para métricas e logs garante facilidade de operação em produção.

## Diagramas

Para visualizar a arquitetura, consulte o diagrama:

![Diagrama Arquitetural](../img/diagrama-arquitetura.png)
