# Teste de código para a CD2

Teste de código usando o Spring Boot para a vaga de Desenvolvedor Fullstack Java Jr da CD2.

## Objetivo

Implementar para empresa de transporte de cargas SigaBem o endpoint para o cálculo do frete e a data prevista de entrega.

Considerar regras para calcular o valor do frete:

* CEPs com DDDs iguais tem 50% de desconto no valor do frete e entrega prevista de 1 dia.
* CEPs de estados iguais tem 75% de deconto no valor do frete e entrega prevista de 3 dias.
* CEPs de estados diferentes não deve ser aplicado o desconto no valor do frete e entrega prevista de 10 dias.
* O valor do frete é cobrado pelo peso da encomenda, o valor para cada KG é R$1,00.

Seu input de entrada deve ser "peso", "cepOrigem", "cepDestino" e "nomeDestinatario"

Utilizar a API gratuita de consulta de CEP abaixo: Documentação da API: https://viacep.com.br/ Exemplo do GET:
https://viacep.com.br/ws/<CEP _A_CONSULTAR>/json/

Endpoit pode ser público Response/Output deve possuir: "vlTotalFrete" e "dataPrevistaEntrega", "cepOrigem" e "cepDestino" Deve ser persistido no banco os valores da cotação os valores consultados: "peso", "cepOrigem", "cepDestino", "nomeDestinatario", "vlTotalFrete", "dataPrevistaEntrega" e "dataConsulta".
## Como rodar a aplicação

Importe o projeto na IDE da sua preferência (eu uso IntelliJ IDEA Ultimate) e aguarde a instalação das dependências (o
projeto usa o Maven para gerenciar as dependências). Após a conclusão da importação, inicie a aplicação. 

Para facilitar, o Spring foi configurado com o Swagger (para visualizar a documentação da API) e com o H2 Console. Os 
links de acesso são:

- http://localhost:8080/swagger-ui/index.html
- http://localhost:8080/h2-console

## Swagger

Disponível no arquivo [swagger/openapi.yml](swagger/openapi.yml). Copie o conteúdo do arquivo e cole no
[Swagger Editor](https://editor.swagger.io/) (ou acesse o Swagger disponível no projeto).

## Exemplos de testes

Use os exemplos a seguir para validar os resultados esperados. Você pode fazer esses testes através do Swagger no link
http://localhost:8080/swagger-ui/index.html (você precisa rodar a aplicação antes de acessar esse link)

### Mesmo DDD (50% de desconto)

Request:

```json
{
  "peso": 200,
  "cepOrigem": "05422-000",
  "cepDestino": "05421-009",
  "nomeDestinatario": "Nirlley Costa"
}
```
Response:

```json
{
  "peso": 200,
  "cepOrigem": "05422-000",
  "cepDestino": "05421-009",
  "vlTotalFrete": 100,
  "dataPrevistaEntrega": "2022-07-12"
}
```

### Mesmo estado (75% de desconto)

Request:

```json
{
  "peso": 200,
  "cepOrigem": "05422-000",
  "cepDestino": "11440-530",
  "nomeDestinatario": "Nirlley Costa"
}
```

Response:

```json
{
  "peso": 200,
  "cepOrigem": "05422-000",
  "cepDestino": "11440-530",
  "vlTotalFrete": 50,
  "dataPrevistaEntrega": "2022-07-14"
}
```

### Estado diferente (sem desconto)

Request:

```json
{
  "peso": 200,
  "cepOrigem": "05422-000",
  "cepDestino": "30310-080",
  "nomeDestinatario": "Nirlley Costa"
}
```

Response:

```json
{
  "peso": 200,
  "cepOrigem": "05422-000",
  "cepDestino": "30310-080",
  "vlTotalFrete": 200,
  "dataPrevistaEntrega": "2022-07-21"
}
```
