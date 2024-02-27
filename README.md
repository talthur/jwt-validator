# Backend Challenge

## Requisitos
- Java 17
- Docker
- Maven 3.6.3

## Como subir
- Clonar projeto
- Na pasta do projeto rodar ```make start```
- Para descer a aplicação, rodar ```make stop```
- Dentro de ./postman existe a collection de requests para o serviço

## Premissas
- Endpoint só deve realizar a verificação do JWT
- As validações podem ser alteradas, acrescentadas, retiradas no futuro

## Decisões
- Recurso POST, passando JWT pelo body, acredito ser semânticamente melhor
- Criação de interface Validator e implementações dessa interface com cada validação de JWT, tornando a solução mais extensiva e de acordo com open-closed principle, onde caso seja necessário alguma nova validação basta criar uma nova implementação da interface.
- JWTValidatorUseCase só decodifica o JWT e passa seu conteúdo para um stream com as validacões, caso todas retornem true, o método devolve o resultado como true para a Controller criar o DTO de saída
- Prometheus e Grafana para métricas (sobe container no docker-compose)
- Zipkin para trace (sobe container no docker-compose)
- Logback para logs
