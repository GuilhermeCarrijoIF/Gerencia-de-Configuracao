package com.backend;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/** Servidor simples do exemplo da disciplina. */
public class App {
    /**
     * Inicia o servidor na porta indicada pelo Railway ou na porta 8080.
     * @param args argumentos do programa
     * @throws IOException se o servidor não puder iniciar
     */
    public static void main(String[] args) throws IOException {
        String portaAmbiente = System.getenv("PORT");
        int porta = portaAmbiente == null ? 8080 : Integer.parseInt(portaAmbiente);
        HttpServer servidor = HttpServer.create(new InetSocketAddress(porta), 0);
        servidor.createContext("/", requisicao -> {
            byte[] resposta = Mensagem.saudacao("mundo").getBytes(StandardCharsets.UTF_8);
            requisicao.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
            requisicao.sendResponseHeaders(200, resposta.length);
            try (var saida = requisicao.getResponseBody()) {
                saida.write(resposta);
            }
        });
        servidor.start();
        System.out.println("Servidor iniciado na porta " + porta);
    }
}
