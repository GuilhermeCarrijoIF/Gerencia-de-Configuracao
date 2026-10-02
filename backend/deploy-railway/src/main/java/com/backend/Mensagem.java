package com.backend;

/** Mensagem apresentada pelo backend. */
public class Mensagem {
    /**
     * Monta uma saudação para o nome informado.
     * @param nome nome da pessoa, podendo estar vazio
     * @return saudação em português
     */
    public static String saudacao(String nome) {
        if (nome == null || nome.isBlank()) {
            return "Olá, visitante!";
        }
        return "Olá, " + nome + "!";
    }
}
