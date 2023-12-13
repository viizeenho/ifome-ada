package com.adaproject.ifood;

import com.adaproject.ifood.model.Entregador;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;

public class UsuarioSteps {

    private RequestSpecification request;
    private ValidatableResponse response;
    private static final String BASE_URL = "http://localhost:8080/ifood";

    @Given("o endpoint {string} do tipo {string}")
    public void setEndpoint(String endpoint, String tipo) {
        RestAssured.baseURI = BASE_URL.concat(endpoint);
        request = RestAssured.given();
    }

    @When("eu enviar uma requisição para salvar um novo usuário com o CPF {string}, nome {string}, tipo de documento {string} e data de vencimento da CNH {string}")
    public void enviarRequest(String cpf, String nome, String tipoDocumento, String dataVencimentoCNH) {
        var usuario = this.criarUsuario(cpf, nome, tipoDocumento, LocalDate.parse(dataVencimentoCNH));
        response = request.body(asJsonString(usuario)).contentType(ContentType.JSON).post().then();
    }

    @Then("eu recebo uma resposta com código {int}")
    public void compararResultado(Integer codigo) {
        response.assertThat().statusCode(codigo);
    }

    @And("conteudo igual CPF {string} e o nome {string}")
    public void conteudoIgualCpfCpfEONomeNome(String cpf, String nome) {
        response.assertThat().body("nome", equalTo(nome));
        response.assertThat().body("cpf", equalTo(cpf));
    }

    @And("conteudo com a mensagem {string}")
    public void conteudoComAMensagemMensagem(String mensagem) {
        response.assertThat().body(equalTo(mensagem));
    }

    private Entregador criarUsuario(String cpf, String nome, String tipoDocumento, LocalDate dataVencimentoCNH) {
        Entregador usuario = new Entregador();
        usuario.setNumeroDocumento(cpf);
        usuario.setNome(nome);
        // Configurar o tipo de documento se necessário
        // usuario.setTipoDocumento(tipoDocumento);
        usuario.setDataVencimentoCNH(dataVencimentoCNH);
        return usuario;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
