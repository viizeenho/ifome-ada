package com.adaproject.ifood.validator;

import java.util.Arrays;
import java.util.List;

public class ContaBancariaValidator {
    private static final List<String> INSTITUICOES_BANCARIAS_VALIDAS = Arrays.asList("Banco A", "Banco B", "Banco C");

    private static final List<String> TIPOS_CONTA_VALIDOS = Arrays.asList("Corrente", "Poupança");

    public static boolean validarNumeroAgencia(String numeroAgencia) {
        return numeroAgencia.matches("\\d+"); //Deve conter apenas dígitos
    }

    public static boolean validarNumeroConta(String numeroConta) {
        return numeroConta.matches("\\d+"); //Deve conter apenas dígitos
    }

    public static boolean validarTipoConta(String tipoConta) {
        return TIPOS_CONTA_VALIDOS.contains(tipoConta);
    }

    public static boolean validarInstituicaoBancaria(String instituicaoBancaria) {
        return INSTITUICOES_BANCARIAS_VALIDAS.contains(instituicaoBancaria);
    }

}