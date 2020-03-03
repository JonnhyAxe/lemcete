# Cetelem code challenge

Pretende-se com este code challenge que se desenvolva um serviço em Spring Boot para gestão de propostas comerciais de Crédito. 
O software em questão deverá permitir a gestão de propostas comerciais bem como a identificação da listagem de clientes 
que faz match com os critérios de uma determinada proposta comercial.

Cada proposta comercial deve ser constituída pelo menos pelos seguintes critérios de informação:
* Nome da proposta comercial
* Perfil de risco [Baixo, Médio, Elevado]
* Gama de idades alvo [18 - 70]
* Zona geográfica [Norte, Centro, Sul]

Por outro lado, cada cliente, deve ser descrito com base nos seguintes critérios:
* Primeiro Nome
* Último Nome
* Email
* Idade [18 - 70]
* Perfil de risco [Baixo, Médio, Elevado]
* Zona geográfica [Norte, Centro, Sul]

## Requisitos:
* Gerar aleatoriamente uma listagem de 1 milhão de clientes e auto popular a BD com essa informação
* Desenvolver CRUD REST para gestão das propostas comerciais
* Criar método REST que devolva lista de clientes que faz match com uma determinada proposta comercial
* Considera-se que um cliente faz match com uma proposta comercial se tiver o mesmo perfil de risco, zona geográfica e idade dentro da gama definida para a proposta
* Para cada cliente que faça match, deverá ser devolvida a totalidade da informação deste
* Criar método REST que devolva lista de propostas comerciais possíveis para um determinado cliente

## Entrega
Será dada relevância aos seguintes pontos:
  * Documentação
  * Estruturação do código
  * Testes

A solução encontrada deverá ser compactada em formato .zip e anexada a um email enviado para [Mauricio.Fernandes@cetelem.pt] com o assunto "BNP Paribas Personal Finance | Interview | Code Challenge".

---

# Pré Requisitos: 

1. Instalação de maven é requisito para compilar e executar a applicação
1. Instalar a lib lombok para efeitos de dev/eclipse https://howtodoinjava.com/automation/lombok-eclipse-installation-examples/
2. Assumiu-se uma relação N-to-N entre Clientes e Propostas Comercais.
3. Inmemory DB
4. Entidades no serviço são identificaveis por IDs. 
5. RepositoryRestResource são expostos apenas para test, mas poderão ser "escondidos" com Spring Security
6. Interface Driven Development and TDD (testes unitarios para todas as camadas, integraçao para a camada de repositorios )

## Estrutura do Projecto
---


```
├─┬ lemcete     				  		→  Java/Spring Boot
│ ├── src
│ └── pom.xml     
│ └── target/site/jacoco/index.html 	→ Reporte de testes unitarios

```


## First App run

Devido ao facto de os requisitos necessitarem de 1 milhão de clientes, criou-se um perfil 'dev' para efeitos de desenvolvimento (apenas com dados de necessarios para teste) e para execução de testes de integração. Adicionalmente, e para testar a criação da quantidade de clientes necessários, o perfil 'prod' deve ser usado, que também contem os dados usados no perfil 'dev'

```
> mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

Para executar com o perfil de prod use o seguinte comando

```
> mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

Para efeitos visuais de teste, usou-se Swagger que pode ser acedido, no seu browser e com qualquer um dos comandos anteriores, com o seguinte link: 

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html )


###  Test de listagens: 

# Criar método REST que devolva lista de clientes que faz match com uma determinada proposta comercial

Poderá usar o seguinte urls ou criar os mesmos pedidos com Swagger. 


[Lista de clientes com proposta comercial perfil de LOW, zona geográfica NORTH e idade dentro 18,45](http://localhost:8080/sallesSell/search/findByRisKProfileAndGeographicalAreaAndComercialSellAgeRanges?risKProfile=LOW&geographicalArea=NORTH&comercialSellAgeRanges=18,45)

As propostas com nomes "Salles under 40" (ID 1) e "Active worker" (ID 4) serão listadas.

Visto que as propostas e cliente tem relação, podemos confirmar os clientes de cada proposta isoladamente. 

[Clientes da proposta "Salles under 40" (ID 1) - http://localhost:8080/sallesSell/1/clients](http://localhost:8080/sallesSell/1/clients)

Neste serão listado o cliente "Joao Machado" (ID 7) e "Luisa Sousa" (ID 9) e que por sua vez podemos [verificar as suas propostas comerciais do Joao - http://localhost:8080/clients/7/sallesSell] (http://localhost:8080/clients/7/sallesSell) [e da Luisa - http://localhost:8080/clients/9/sallesSell](http://localhost:8080/clients/9/sallesSell]) , que será respectivamente igual "Salles under 40" (ID 1) e "Active worker" (ID 4) para o João e  "Salles under 40" para a Luisa. 

[Clientes da proposta "Active worker" (ID 4)](http://localhost:8080/sallesSell/4/clients)

Neste será apenas listado o cliente "Joao Machado" (ID 7)


# Criar método REST que devolva lista de propostas comerciais possíveis para um determinado cliente

Visto que no teste anterior realiza-se pesquisas das propostas comerciais possíveis para um determinado cliente,
fica apenas listados os URLs necessarios para testar novamente

[Todos os clientes - http://localhost:8080/clients](http://localhost:8080/clients)

[informacação do cliente Joao Machado - http://localhost:8080/clients/7](http://localhost:8080/clients/7)


## Teste CRUD para propostas comerciais

# 1. Listar clients para a proposta com idades '18,45', are geografica do 'NORTH' e risco 'LOW'

Deverá usar o método swagger e adicionar os parametros mencionados 
 
http://localhost:8080/swagger-ui.html#/salles-controller/getClientBySallesCriteriaUsingGET

Devera ser listado o seguinte Json 

```json
[
  {
    "id": 7,
    "firstName": "Joao",
    "lastName": "Machado",
    "email": null,
    "age": 35,
    "risKProfile": "LOW",
    "geographicalArea": "NORTH"
  },
  {
    "id": 9,
    "firstName": "Luisa",
    "lastName": "Sousa",
    "email": null,
    "age": 33,
    "risKProfile": "LOW",
    "geographicalArea": "NORTH"
  }
]
```

# 2. Listar as propostas do cliente com ID 7

Deverá usar o método swagger e adicionar o id 7 

http://localhost:8080/swagger-ui.html#/client-controller/getClientSallesByIdUsingGET

Devera ser listado o seguinte Json 

```json
[
  {
    "id": 4,
    "name": "Active worker",
    "risKProfile": "LOW",
    "comercialSellAgeRanges": "18,45",
    "geographicalArea": "NORTH"
  },
  {
    "id": 1,
    "name": "Salles under 40",
    "risKProfile": "LOW",
    "comercialSellAgeRanges": "18,45",
    "geographicalArea": "NORTH"
  }
]
```

# 3. atualizar uma das propostas (ID 4) listas para o Cliente Joao Machado (ID 7)

Deverá usar o método swagger e usar o seguinte json com a alteraçao do perfil de risco para elevado

http://localhost:8080/swagger-ui.html#/salles-controller/upateSallesSellUsingPUT

```json
  {
    "id": 4,
    "name": "Active worker",
    "risKProfile": "HIGH",
    "comercialSellAgeRanges": "18,45",
    "geographicalArea": "NORTH"
  }
```

Deverá obter um status HTTP 202

# 4. Listar novamente as propostas do cliente com ID 7

Deverá obter o seguinte Json, onde se pode verificar que o risco da proposta com ID 4 é agora elevado

```json
[
  {
    "id": 4,
    "name": "Active worker",
    "risKProfile": "HIGH",
    "comercialSellAgeRanges": "18,45",
    "geographicalArea": "NORTH"
  },
  {
    "id": 1,
    "name": "Salles under 40",
    "risKProfile": "LOW",
    "comercialSellAgeRanges": "18,45",
    "geographicalArea": "NORTH"
  }
]
```

## Possiveis melhorias: 
 
DTO e MapStruct
BDD reports with Cucumber
Cache
Spring Reactor Web with non blocking Netty server
