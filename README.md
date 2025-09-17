Ticket 🧾

CRUD reativo de Tickets usando Spring Boot + WebFlux + H2.
Projeto simples, direto e pronto para servir de base para estudos ou bootstrap de APIs reativas.

➡️ Repositório no GitHub

🚀 Stack

Java 21+

Spring Boot (WebFlux)

H2 (memória) via R2DBC

(Opcional) MapStruct para mapeamento de DTOs

Testes com JUnit 5 / Reactor Test

🧩 Domínio (Entidade)
class Ticket {
  Long id;          // auto-increment
  String title;
  String description;
}


Dica: exponha DTOs no controller e mantenha a entidade isolada (bom pra evoluir sem quebrar clientes).

🌐 Endpoints

Base URL: /api/v1/tickets

Método	Rota	Descrição	Sucesso
GET	/	Lista todos (Flux)	200 OK
GET	/{id}	Busca por id (Mono)	200 OK / 404 Not Found
POST	/	Cria ticket	201 Created
PUT	/{id}	Atualiza ticket	200 OK / 404 Not Found
DELETE	/{id}	Remove ticket	204 No Content / 404 Not Found
Exemplos (cURL)
# Criar
curl -X POST http://localhost:8080/api/v1/tickets \
  -H "Content-Type: application/json" \
  -d '{ "title": "Primeiro", "description": "Ticket de exemplo" }'

# Listar
curl http://localhost:8080/api/v1/tickets

# Buscar por ID
curl http://localhost:8080/api/v1/tickets/1

# Atualizar
curl -X PUT http://localhost:8080/api/v1/tickets/1 \
  -H "Content-Type: application/json" \
  -d '{ "title": "Atualizado", "description": "Descrição nova" }'

# Deletar
curl -X DELETE http://localhost:8080/api/v1/tickets/1 -i

▶️ Como rodar

Pré-requisitos:

Java 21+

Maven 3.9+

Passos:

git clone https://github.com/joaoAlbertinoNeto/ticket
cd ticket
./mvnw spring-boot:run          # ou: mvn spring-boot:run


App em: http://localhost:8080

⚙️ Configuração (R2DBC + H2)

application.yml (exemplo):

spring:
  application:
    name: ticket
  r2dbc:
    url: r2dbc:h2:mem:///ticketdb;DB_CLOSE_DELAY=-1;MODE=LEGACY
    username: sa
    password:
  h2:
    console:
      enabled: true
      path: /h2-console
  sql:
    init:
      mode: never  # usando R2DBC (sem JDBC). Ajuste para 'always' se for usar schema.sql com JDBC


Nota H2 Console: O console do H2 é JDBC. Se quiser usar o /h2-console, adicione também uma URL JDBC para inspeção (sem afetar o R2DBC):

spring:
  datasource:
    url: jdbc:h2:mem:ticketdb;DB_CLOSE_DELAY=-1;MODE=LEGACY
    driver-class-name: org.h2.Driver
    username: sa
    password:


Console: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:ticketdb

User: sa (sem senha)

🧪 Testes
./mvnw test


Dicas para testes reativos:

Use StepVerifier (Reactor Test) para validar Mono/Flux.

Evite block() em produção; só em testes/bootstraps.

🏗️ Estrutura sugerida
src/main/java/...
  ├─ application/        # casos de uso (serviços)
  ├─ domain/             # entidades e portas (ports)
  ├─ infrastructure/     # adapters (repository, mappers)
  └─ interfaces/         # controllers WebFlux (API)


Segue o estilo Ports & Adapters (Hexagonal): controllers chamam port in (use cases), que dependem de port out (repositórios), implementados nos adapters.

✅ Boas práticas REST já aplicadas

DELETE → 204 No Content

findById vazio → 404 Not Found

POST → 201 Created (preferir Location no header com URL do recurso)

Validação com Bean Validation (@Valid) nos DTOs (quando aplicável)

🗺️ Roadmap (ideias)

 Paginação no GET /tickets

 Bean Validation nos DTOs (title obrigatório, tamanhos, etc.)

 Observabilidade: Actuator + métricas

 Perfil prod com Postgres (R2DBC) + Docker Compose

 Integração com MapStruct para mapeamento Entity ↔ DTO

🧾 Licença

MIT (ou a de sua preferência).

🔗 Links

Repositório: https://github.com/joaoAlbertinoNeto/ticket

H2 Console (local): http://localhost:8080/h2-console