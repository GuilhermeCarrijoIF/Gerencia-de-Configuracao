# Trabalho de Gerência de Configuração

Entrega 2: sistema de construção que atende às tarefas do slide 36.

Este projeto foi adaptado da [base do professor](https://github.com/felipegaiaifgoiano/GC).
Foram mantidos o frontend em HTML/CSS, o backend Java 17 com Maven e os dois
workflows. Foram acrescentados testes, scripts de construção, relatórios e Javadoc.
O backend agora inicia um servidor HTTP, para poder funcionar no Railway.

## Executar no computador

É necessário ter Java 17 e Maven instalados. Na pasta do projeto:

```sh
cd backend/deploy-railway
mvn verify javadoc:javadoc surefire-report:report-only
java -jar target/projeto-railway-1.0-SNAPSHOT.jar
```

Acesse http://localhost:8080 para ver a mensagem. Para encerrar, pressione Ctrl+C.
O frontend pode ser aberto pelo arquivo `frontend/index.html`.

## Tarefas do slide 36

| Tarefa | Como foi atendida |
| --- | --- |
| Geração de script de construção | O Maven gera `construir.bat` e `construir.sh` em `target/scripts/`. |
| Integração com o SCV | Os workflows fazem checkout do Git e executam a construção em push e pull request. |
| Recompilação mínima | O compilador compara as datas dos arquivos e recompila os fontes alterados. |
| Criação do sistema executável | O Maven gera um JAR executável com a classe `com.backend.App`. |
| Automação de testes | O JUnit executa três testes. Uma falha interrompe a construção. |
| Relatórios | São gerados resultados dos testes em XML e um relatório HTML. |
| Geração de documentação | O Javadoc gera a documentação HTML das classes Java. |

Os resultados ficam em `backend/deploy-railway/target/`:

- Executável: `projeto-railway-1.0-SNAPSHOT.jar`.
- Relatório: `site/surefire.html`.
- Documentação: `site/apidocs/index.html`.
- Scripts: `scripts/construir.bat` e `scripts/construir.sh`.

Depois da primeira construção, é possível executar `target\scripts\construir.bat`
no Windows ou `sh target/scripts/construir.sh` no Linux.

Para demonstrar a recompilação mínima, execute `mvn compile` duas vezes, sem
`clean`. A segunda execução mostra `Nothing to compile`. Se mudar apenas uma
mensagem em `App.java`, somente essa classe recompila. Ao mudar assinaturas de
métodos ou remover classes, use `mvn clean verify` para atualizar os dependentes.

## GitHub Actions e deploy

Acompanhe as execuções e baixe os artefatos na
[aba Actions](https://github.com/GuilhermeCarrijoIF/Gerencia-de-Configuracao/actions).

O frontend é publicado no GitHub Pages. Em **Settings → Pages**, selecione
**GitHub Actions** como fonte da publicação.

O deploy do backend está preparado, mas depende de uma conta no Railway:

1. Crie um projeto com um serviço vazio no Railway, sem conectar o GitHub.
2. Crie um token de projeto e salve-o no GitHub como secret `RAILWAY_TOKEN`.
3. Em **Settings → Secrets and variables → Actions → Variables**, crie
   `RAILWAY_SERVICE` com o nome ou ID do serviço e `DEPLOY_RAILWAY` com `true`.
4. Execute o workflow **Construcao do backend** ou envie uma alteração para `main`.
5. No Railway, gere um domínio em **Settings → Networking** e abra a URL.

O workflow envia o JAR aprovado nos testes. O Dockerfile coloca esse JAR em
um ambiente Java 17. Sem `DEPLOY_RAILWAY=true`, o build e os testes continuam
funcionando e a etapa de deploy fica desativada.
