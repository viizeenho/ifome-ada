Feature: Operações CRUD para usuário

#  Scenario: Criar um novo usuário
#    Given o endpoint "/api/v1/usuarios" do tipo "POST"
#    When eu enviar uma requisição para salvar um novo usuário com o CPF "12345678910"
#    Then eu recebo uma resposta com código 201
#
#  Scenario: Criar um usuário com o cpf já existente
#    Given o endpoint "/api/v1/usuarios" do tipo "POST"
#    When eu enviar uma requisição para salvar um novo usuário com o CPF "12345678910" e Nome "Hugo"
#    Then eu recebo uma resposta com código 400
#
#  Scenario: Criar um usuário com cpf inválido com 9 digitos
#    Given o endpoint "/api/v1/usuarios" do tipo "POST"
#    When eu enviar uma requisição para salvar um novo usuário com o CPF "1234567891"
#    Then eu recebo uma resposta com código 400

  Scenario Outline: Criação de usuários com sucesso
    Given o endpoint <endpoint> do tipo <tipo>
    When eu enviar uma requisição para salvar um novo usuário com o CPF <cpf>, nome <nome>, tipo de documento <tipoDocumento> e data de vencimento da CNH <dataVencimentoCNH>
    Then eu recebo uma resposta com código <codigo>
    And o conteúdo contém CPF <cpf> e o nome <nome>

    Examples:
      | endpoint                 | tipo  | nome       | cpf           | tipoDocumento | dataVencimentoCNH  |
      | "/api/v1/entregadores"   | "POST"| "vinicius"  | "45846879861" | "CPF"         | "2023-12-13"      |
#  Scenario Outline: Criação de usuários com falhas
#    Given o endpoint <endpoint> do tipo <tipo>
#    When eu enviar uma requisição para salvar um novo usuário com o CPF <cpf>
#    Then eu recebo uma resposta com código <codigo>
#    And conteudo com a mensagem <mensagem>
#
#    Examples:
#      |       endpoint     |  tipo  |      cpf      |  codigo  |              mensagem          |
#      | "/api/v1/entregadores" | "POST" | "12345678910" |    400   |    "O CPF informado já existe" |
#      | "/api/v1/entregadores" | "POST" | "1234567891"  |    400   |  "O CPF informado não é valido"|
