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

### Instalação
1. Clone este repositório:
```java
git clone https://github.com/seu-usuario/analisador-logs.git
cd analisador-logs
```
2. Compile e execute o projeto:
```java
mvn spring-boot:run
```
3. Acesse a aplicação no navegador:
```java
http://localhost:8080
```

### Uso
**Upload de Arquivos de Log**
1. Acesse a interface web.
2. Faça o upload de um arquivo .txt contendo logs no formato:
```java
[2023-12-01 10:00:00] INFO - Application started
[2023-12-01 10:01:00] ERROR - NullPointerException
[2023-12-01 10:02:00] WARN - Disk space low
```
**Aplicação de Filtros**
1. Escolha o nível desejado no menu suspenso (INFO, WARN ou ERROR).
2. Clique em **Aplicar Filtro** para atualizar o gráfico.

**Relatórios CSV**
Use o endpoint `/logs/report?level=LEVEL` para baixar relatórios CSV filtrados.