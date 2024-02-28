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

- Curl para um caso de sucesso
```
curl --location 'http://localhost:8080/v1/jwt/validate' \
--header 'Content-Type: application/json' \
--data '{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg"
}'
```

Exemplo de retorno:
```
{
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg",
    "valid": true
}
```

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

## Métodos
- ClaimValidator.validate() -> retorna se no JWT possui as 3 claims necessárias e se possui um tamanho de apenas 3.
- NameValidator.validate() -> caso não exista um Claim Name, retorna False. Caso exista, valida utilizando um regex para verificação de números.
- RoleValidator.validate() -> caso não exista um Claim Role, retorna False. Caso exista, valida se o valor é algum dos permitidos, iterando sobre os valores permitidos para isso.
- SeedValidator.validate() -> caso não exista um Claim Sole, retorna False. Caso exista, valida se o número é divisivel por algum outro número que não ele mesmo ou 1.
