# Bionexo UBS Test

## Para rodar o microservice, há duas opções:

## Via docker

``` 
mvn clean install

docker-compose -f docker-compose.yml up -d

```

## Via Spring
```

mvn spring-boot:run

```

## Executar testes

```
mvn test
```

## LOCAL HOST

### Swagger
http://localhost:8080/swagger-ui.html

## HAL Browser
http://localhost:8080/browser/index.html

## SERVER HOST

### Swagger
https://ubs-microservice.herokuapp.com/swagger-ui.html

## HAL Browser
https://ubs-microservice.herokuapp.com
