# Analisador de Logs
Um projeto em Java com Spring Boot para analisar arquivos de logs, gerar métricas e exibir relatórios de forma visual e interativa. O sistema permite:

- Upload de arquivos de logs para processamento.
- Filtros dinâmicos para visualizar logs por nível (INFO, WARN, ERROR).
-  Visualização de métricas em gráficos interativos.

### Funcionalidades
1. Upload de Logs
    - Enviar arquivos de logs no formato .txt via interface web.
    - Processamento e armazenamento de logs no back-end.
2. Filtros Dinâmicos
    - Filtrar logs por nível (INFO, WARN, ERROR) na interface web.
    - Atualizar gráficos interativos automaticamente. 
3. Visualização de Métricas
    - Exibição de gráficos de barras com métricas sobre os logs processados.
    - Dados atualizados dinamicamente com base nos filtros aplicados.

### Tecnologias Utilizadas
- Backend:
    - Java 17
    - Spring Boot
    - Apache Commons CSV (geração de relatórios em CSV)       
- Frontend:
    - HTML, CSS, JavaScript
    - Chart.js (gráficos interativos)
    - Fetch API (comunicação com o back-end) 
- Testes:  
    - JUnit 5
    - Mockito

### Pré-requisitos
- Java 17+
- Maven
- Navegador web moderno para acessar a interface.