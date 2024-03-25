# Pagamenton-kafka-mensageria

## Tecnologias utilizadas
* Java
* Spring Boot
* Docker
* Apache Kafka
* Control Center
* Schema Registry
* Banco de dados H2

## Estrutura do Projeto
O projeto é dividido em dois microserviços, cada um responsável por uma parte específica do processo de pagamento.

### Pagamento
O módulo de Pagamento é responsável por realizar o processo de criação de pagamentos. Este módulo aceita solicitações de criação de pagamento e executa as etapas necessárias para processar e registrar o pagamento no sistema.

* Criação de Pagamento: Aceita solicitações para criar um novo pagamento.
* Registro de Pagamento: Registra o pagamento no banco de dados.
  
### Validacao_pagamento
O módulo Validacao_pagamento é responsável por realizar o processo de validação de pagamentos. Ele recebe solicitações de validação de pagamento e executa as verificações necessárias para garantir que o pagamento atenda a todos os critérios definidos pelo sistema.

* Validação de Segurança: Verifica a autenticidade e integridade das informações do pagamento para evitar fraudes.

![clideo_editor_cf7df45b971946d7a2d32f0a49c89d0e-ezgif com-crop](https://github.com/LuandsonAlves/Pagamento-kafka-mensageria/assets/90480558/ef232a76-b0e8-4470-a9ec-951ecd8abee7)
