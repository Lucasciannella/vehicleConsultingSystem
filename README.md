# Vehicle system Api 

> Sistema de consulta veicular da tabela fipe


### O projeto:

Visando facilitar a consulta de dados da tabela fipe 
esta api é capaz de buscar informações sobre carros e auditalas
em serviços da AWS.

Apesar do seu escopo reduzido a consulta de veículos do tipo *carro*, o desenvolvedor
que necessitar desta api poderá facilmente escalar a aplicação para a sua necessidade, tendo em vista que toda api é aberta a receber 
modificações sem tanto custo operacional.

### Tecnologias:

Foram utilizadas no desenolvimento dessa api:

- jdk 17 
- Spring boot 
- Docker /  Docker-compose
- AWS S3  
- Redis 

### instruções / inicialização:

É necessário configurar suas credencias da AWS  de antemão no arquivo **docker-compose.yml**. 
como podem acompanhar no exemplo abaixo:

```yaml
    version: '3'
    services:
      cache:
        image: redis:latest
        command: [ "redis-server", "--protected-mode", "no" ]
        container_name: redis
        ports:
          - "6379:6379"
        expose:
          - 6379
      vehicle-api:
        build: .
        container_name: vehicle_api
        image: lucasciannela/vehicle-api
        restart: always
        links:
          - cache
        ports:
          - 8080:8080
        environment:
          - SPRING_ENVIRONMENT= prod
          - AWS_ACCESS_KEY_ID= #adicionar credenciais aws 
          - AWS_SECRET_ACCESS_KEY= #adicionar credenciais aws
          - AWS_REGION= #adicionar credenciais aws
          - AWS_S3_BUCKET_NAME= #adicionar credenciais aws
          - AWS_ENDPOINT= #adicionar credenciais aws

```

Após a configuração das suas credenciais devemos usar a instrução do maven abaixo para garantir 
que teremos o build correto e atualizado da aplicação:

```mvn 
   mvn clean install
```

Após o build da aplicação podemos finalmente executar os containers docker,
a partir daqui temos duas variações.

#### Ambiente de desenvolvimento/local:

com seu projeto buildado e configurado pronto para ser executado, subir o container do 
redis para que a aplicação funcione, utilizaremos a instrução:

```shell
  docker-compose up -d cache 
```

em seguida podemos rodar a aplicação normalmente via intelij ou utilizando a instrução:
```mvn
    mvn spring-boot:run   
```

### Ambiente de produção:

Com toda aplicação configurada para rodar o ambiente de produção utilziaremos
da instrução:

````shell
  docker-compose up  
````

### Sugestões e considerações finais:
