# RT Dias 3D SaaS — deploy

O deploy usa Vercel para a interface React, Render para a API Spring Boot e uma hospedagem MySQL externa para os dados. O Render usa o Dockerfile apenas para compilar e executar a API Java, pois Blueprints não possuem runtime Java nativo.

## 1. Hospedagem MySQL

Crie um banco MySQL em uma plataforma de sua preferência. Ela deve permitir conexões externas a partir do Render. Guarde o host, porta, nome do banco, usuário e senha.

## 2. Render (API)

No Render, crie um Blueprint a partir deste repositório; o arquivo `render.yaml` cria a API. Informe as variáveis solicitadas:

- `SPRING_DATASOURCE_URL`: URL JDBC MySQL, por exemplo `jdbc:mysql://HOST:3306/NOME_DO_BANCO?useSSL=true&requireSSL=true`.
- `SPRING_DATASOURCE_USERNAME` e `SPRING_DATASOURCE_PASSWORD`: usuário e senha do MySQL.
- `APP_CORS_ALLOWED_ORIGINS`: domínio final da Vercel, como `https://meu-app.vercel.app`.

`JWT_SECRET` e `ADMIN_SECRET` são gerados pelo Render. Após o deploy, copie a URL pública da API, por exemplo `https://rtdias3d-api.onrender.com`.

## 3. Vercel (interface)

Importe o mesmo repositório no Vercel e defina **Root Directory** como `react-test/my-app`. Antes de publicar, cadastre a variável de ambiente de produção `REACT_APP_API_URL` com a URL pública da API do Render. Faça um novo deploy após alterar essa variável.

## Verificação

Abra a URL da Vercel e faça login. A API responde em `https://SUA_API.onrender.com/actuator/health`.

As credenciais antigas foram removidas do código. Gere novas credenciais no banco e não publique arquivos `.env`.
