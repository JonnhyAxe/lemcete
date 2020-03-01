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


Tarefas: 
* Model Cliente e proposta (lombok)
* Repository (H2 DB, JPA spring data)
* Service layer (propostas comerciais, listagem de clientes) + Unit Testing 
* Controler (Unit testing with Validations)
* 