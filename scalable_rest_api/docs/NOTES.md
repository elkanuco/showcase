# RESTful API : fund transfer "showcase"

- Docker/Docker compose used to deploy the setup
- Maven 3.9.9 + JDK 21
- Mock service to serve basic GET call with a random exchange-rate
  - implemented using expressjs wrapped with docker
  - <https://expressjs.com/>
- Declarative REST Client using Feign through Spring
  - <https://github.com/OpenFeign/feign>
- Database preparation: : Docker image, model definition and initialization script
- Using Spring Boot initializer
  - <https://start.spring.io/>
    - Spring Data
    - Spring Data REST
      - REST API for CRUD operations on the different JPA entities (kept for added flexibility under /datarestapi)
      - e.g.: GET /subjects, POST /subjects, GET /subjects/{id}, PUT /subjects/{id}, DELETE /subjects/{id}
      - this will serve in addition to the sql scripts to help with the initialization of the dummy data
      - Relationships (like accounts per subject) are navigable via REST links.
      - JSON representations follow the HAL specification by default.
      - HAL Browser
        - <http://fundtransfer.localhost/datarestapi/explorer/index.html#uri=/datarestapi>
    - Spring REST
      - OpenAPI 3.0 integrated with Swagger UI
      - Api-docs
        - <http://fundtransfer.localhost/api-docs.yaml>
        - <http://fundtransfer.localhost/api-docs>
      - UI
        - <http://fundtransfer.localhost/swagger-ui/index.html>
- Using Lombok for basic boilerplate code
  - <https://projectlombok.org/>
- Using mapstruct for DTO mapping
  - <https://mapstruct.org/>
- Basic DTO validation with Spring
- Spring, Mockito, assertj, Rest-Assured and TestContainers for tests
  - <https://site.mockito.org/>
  - <https://rest-assured.io/>
  - <https://assertj.github.io/doc/>
  - <https://testcontainers.com/>
- Added traefik redirecting port 80 to 8080 of the spring boot app and doing load balancing when scaling is applied
  - <http://localhost:8080/dashboard/#/http/services/fundtransfer@docker>
- Added Redis to serve as distributed cache and distributed lock on the accounts
  - <https://redis.io/>
- Using experimental Maven plugin to reduce size of the jar and load dependencies on startup
  - <https://github.com/dsyer/spring-boot-thin-launcher>

## Useful commands

- deploy the containers ./setup.sh
- scale fund-transfer-service up to 3 nodes ./scale.sh
- [not needed] run backend on console ./mvnw spring-boot:run or mvn spring-boot:run
- [not needed] repackage ./mvnw clean install or mvn clean install
- [not needed] execute on console get to get random exchange rate: curl "<http://localhost:3000/exchange-rate?base=USD&target=EUR>"
  - <https://man7.org/linux/man-pages/man1/curl.1.html>
- access swager ui and using OpenAPI documentation files:  <http://fundtransfer.localhost/swagger-ui/index.html>
  - useful to get curl request templates
- access HAL Browser: <http://fundtransfer.localhost/datarestapi/explorer/index.html#uri=/datarestapi>
  - useful to navigate through the data and also get curl request templates
- inspect Redis
  - docker exec -it redis redis-cli MONITOR
  - docker exec -it redis redis-cli
    - KEYS *
    - HKEYS exchangeRates

## potential TODO's to "enrich" this show case

- full test coverage (potentially ignoring negligeable parts)
- integrate HashiCorp Vault or any other solution for sharing secrets
- integrate Liquibase or Flyway
- db replication, integrate db proxy
- deploy local registry
- transition into swarm or kubernetes
- integrate a tool to stress test api : Jmeter, Gatling
- integrate a tool to monitor metrics: graphana, prometheus, datadog, elk

| [Back](../README.md)|
|--------|
