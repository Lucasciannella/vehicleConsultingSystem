## Vehicle system Api 🚀

> Sistema de consulta veicular da tabela fipe

### Tecnologias 💻:

Foram utilizadas no desenolvimento dessa api:

- jdk 17
- Spring boot
- Docker / Docker-compose
- AWS S3
- Redis

### instruções / inicialização 📃:

É necessário configurar suas credencias da AWS de antemão no arquivo **docker-compose.yml**.
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

```shell
  mvnw.cmd clean install #no windows
  
 ./mvnw clean install #no linux
```

Após o build da aplicação podemos finalmente executar os containers docker,
a partir daqui temos duas variações:

### Ambiente de desenvolvimento/local:

Após os passos anteriores se você deseja rodar a aplicação em ambiente de dev/local devera subir o container
redis,para isto, utilizamos a instrução:

```shell
  docker-compose up -d cache 
```

em seguida podemos rodar a aplicação normalmente via intelij ou utilizando a instrução:

```shell
    mvnw.cmd clean install #no windows

    ./mvnw spring-boot:run   #no linux
```

### Ambiente de produção:

Para rodar o ambiente de produção, após ter configurado corretamente seu D ockerfile com as variaveis do s3
você podera utilizar o a instrução:

````shell
  docker-compose up  
````

OBS: é de extrema importância que você tenha o docker instalado em seu SO para o funcionamento correto da aplicação.

### Observabilidade🔎:

Podemos verificar metricas da aplicação utilizando dos caminhos que o spring actuator nos fornece.

- Para vizualizar o health check do servidor:
    - ***localhost:8080/actuator/health***
- Para vizualizar as possíveis metricas:
    - ***localhost:8080/actuator/metrics***
- Para vizualizar as informações da aplicação:
    - ***localhost:8080/actuator/info***

### Sugestões e considerações finais📗:

- Escalabilidade:
    -  é possível utilizar o serviço elasticache da amazon com o redis.
    -  é possível transfomar a classe **vehicle** em um mapeamento de entidade, relacional ou não, dependendo da necessidade.