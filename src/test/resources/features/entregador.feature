Feature: Criação de Entregador

  Scenario Outline: Criar um entregador com dados válidos
    Given o endpoint <endpoint> do tipo <tipo>
    When eu enviar uma requisição para salvar um novo usuário com o nome <nome>, NumeroDocumento <numeroDocumento>, tipoDocumento <tipoDocumento> e datVencimentoCNH <dataVencimentoCNH>
    Then o sistema retorna um código de status HTTP 200

    Examples:
      |       endpoint      |   tipo      |        nome          | numeroDocumento   | tipoDocumento   | dataVencimentoCNH |
      |"/ifood/entregadores"|   "POST"    | "Nome do Entregador" | "12345678901"     | "CPF"           | "2023-12-31"      |


#  Scenario Outline: Criar um entregador com dados válidos
#    Given o endpoint <endpoint> do tipo <tipo>
#    When eu enviar uma requisição para salvar um novo usuário com o nome <nome>, NumeroDocumento <numeroDocumento>, tipoDocumento <tipoDocumento> e datVencimentoCNH <dataVencimentoCNH> invalido
#    Then o sistema retorna um código de status HTTP 400
#
#    Examples:
#      |       endpoint      |   tipo      |        nome          |        numeroDocumento   | tipoDocumento   | dataVencimentoCNH |
#      |"/ifood/entregadores"|   "POST"    | "Nome do Entregador" |        "16465465"        | "CPF"           | "2023-12-31"      |