package com.backend;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MensagemTest {
    @Test
    void saudaPeloNome() {
        assertEquals("Olá, Guilherme!", Mensagem.saudacao("Guilherme"));
    }

    @Test
    void usaVisitanteQuandoNomeEstaVazio() {
        assertEquals("Olá, visitante!", Mensagem.saudacao(" "));
    }

    @Test
    void usaVisitanteQuandoNomeNaoFoiInformado() {
        assertEquals("Olá, visitante!", Mensagem.saudacao(null));
    }
}
