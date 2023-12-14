package com.adaproject.ifood;

import com.adaproject.ifood.ENUM.TipoDocumento;
import com.adaproject.ifood.model.dto.EntregadorDTO;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CriarEntregadorSteps {

    private static final String BASE_URL = "http://localhost:8080"; // Atualize para a URL real da sua API
    private RestTemplate restTemplate = new RestTemplate();
    private String endpoint;
    private String tipo;
    private String nome;
    private String numeroDocumento;
    private String tipoDocumento;
    private String dataVencimentoCNH;
    private ResponseEntity<String> responseEntity;

    @Dado("o endpoint {string} do tipo {string}")
    public void o_endpoint_do_tipo(String endpoint, String tipo) {
        this.endpoint = endpoint;
        this.tipo = tipo;
    }

    @Quando("eu enviar uma requisição para salvar um novo usuário com o nome {string}, NumeroDocumento {string}, tipoDocumento {string} e datVencimentoCNH {string}")
    public void eu_enviar_uma_requisicao_para_salvar_um_novo_entregador_com_os_dados_fornecidos(String nome, String numeroDocumento, String tipoDocumento, String dataVencimentoCNH) {
        this.nome = nome;
        this.numeroDocumento = numeroDocumento;
        this.tipoDocumento = tipoDocumento;
        this.dataVencimentoCNH = dataVencimentoCNH;

        String url = BASE_URL + endpoint;
        HttpHeaders headers = new HttpHeaders();

        EntregadorDTO entregadorDTO = new EntregadorDTO();
        entregadorDTO.setNome(nome);
        entregadorDTO.setNumeroDocumento(numeroDocumento);
        entregadorDTO.setTipoDocumento(Enum.valueOf(TipoDocumento.class, tipoDocumento));
        entregadorDTO.setDataVencimentoCNH(LocalDate.parse(dataVencimentoCNH));
        HttpEntity<EntregadorDTO> requestEntity = new HttpEntity<>(entregadorDTO, headers);
        responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
    }

    @Então("o sistema retorna um código de status HTTP 200")
    public void o_sistema_retorna_um_codigo_de_status_HTTP_200() {
        assertNotNull(responseEntity);
        assertEquals("Código de status incorreto", HttpStatus.OK, responseEntity.getStatusCode());
    }

    //segundo cenario


    @Quando("eu enviar uma requisição para salvar um novo usuário com o nome {string}, NumeroDocumento {string}, tipoDocumento {string} e datVencimentoCNH {string} invalido")
    public void eu_enviar_uma_requisicao_para_salvar_um_novo_entregador_com_os_dados_fornecidos_invalido(String nome, String numeroDocumento, String tipoDocumento, String dataVencimentoCNH) {
        this.nome = nome;
        this.numeroDocumento = numeroDocumento;
        this.tipoDocumento = tipoDocumento;
        this.dataVencimentoCNH = dataVencimentoCNH;

        String url = BASE_URL + endpoint;
        HttpHeaders headers = new HttpHeaders();

        EntregadorDTO entregadorDTO = new EntregadorDTO();
        entregadorDTO.setNome(nome);
        entregadorDTO.setNumeroDocumento(numeroDocumento);
        entregadorDTO.setTipoDocumento(Enum.valueOf(TipoDocumento.class, tipoDocumento));
        entregadorDTO.setDataVencimentoCNH(LocalDate.parse(dataVencimentoCNH));
        HttpEntity<EntregadorDTO> requestEntity = new HttpEntity<>(entregadorDTO, headers);
        responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
    }

//    @Então("o sistema retorna um código de status HTTP 400")
//    public void o_sistema_retorna_um_codigo_de_status_HTTP_400() {
//        assertNotNull(responseEntity);
//        assertEquals("Erro para cadastrar entregador: CPF deve conter 11 dígitos.", HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//
//        // Adicione uma verificação para a mensagem de erro
//        String responseBody = (responseEntity.getBody() != null) ? responseEntity.getBody().toString() : "";
//        assertTrue("Erro para cadastrar entregador: CPF deve conter 11 dígitos.", responseBody.contains("Erro para cadastrar entregador: CPF deve conter 11 dígitos."));
//    }

}
