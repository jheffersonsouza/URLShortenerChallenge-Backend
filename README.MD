# Desafio Técnico - Sistema Encurtador de URL

## Contexto
Você foi contratado para desenvolver uma API simples para um sistema de **encurtamento de URLs**, semelhante ao Bit.ly. A aplicação inicialmente funcionará com armazenamento em memória, mas alternativas são aceitas.

O objetivo é avaliar suas habilidades de desenvolvimento backend: arquitetura de código, organização, clareza, tratamento de erros e boas práticas.

---

## Regras

- A linguagem de programação e os frameworks utilizados são de **livre escolha**.
- O `shortCode` deve ser **curto** e conter **apenas caracteres alfanuméricos** (`A-Z`, `a-z`, `0-9`).
- O shortCode pode ser gerado automaticamente por alguma biblioteca.
- URLs e shortCodes podem ser armazenados **em memória**. Persistência em arquivo ou banco é opcional.
- Devem ser usados status HTTP adequados:
    - `201 Created` para criação bem-sucedida no encurtamento.
    - `404 Not Found` para quando o código encurtado não for encontrado.

---

## Endpoints

### `POST /shorten`
- Recebe um JSON com a URL original:
  ```json
  { "url": "https://example.com" }
  ```
- Retorna um JSON com a URL encurtada:
  ```json
  { "shortUrl": "http://localhost:3000/abc123" }
  ```
- Respostas:
    - `201 Created` em caso de sucesso.

---

### `GET /{shortCode}`
- Acessa a URL encurtada e **redireciona** para a URL original.
- Se o shortCode não existir:
    - Retorna `404 Not Found`.

---

## Requisitos Opcionais (Desafios Extras)

Esses itens são opcionais, mas agregarão pontos positivos.

- **Persistência de dados**:
    - Armazenar URLs em arquivo local (ex: JSON) ou banco de dados (ex: MongoDB).

- **Expiração de URLs**:
    - URLs expiram após determinado tempo (ex: 24 horas).

- **Rate limiting**:
    - Limitar o número de encurtamentos por IP (ex: 5 por minuto).

- **Customização de shortCode**:
    - Permitir que o usuário escolha um shortCode customizado, validando unicidade e formato.

- **Validação da URL (entrada)**:
    - Verificar se a URL recebida é válida e retorna um erro se for preciso.

- **Deploy**:
    - Publicar a aplicação em ambiente gratuito (ex: Vercel, Render, Railway).

- **Testes**:
    - Criar testes unitários e/ou de integração.

---
