# Guia de Estudo - Projeto CRUD com Spring Boot

Este README agora e um material de estudo, explicando **o que cada parte do projeto faz** e **o que voce deve revisar** para fixar Java + Spring Boot.

## Checklist de estudo (ordem sugerida)

- [ ] Entender o que e um projeto Maven e para que serve o `pom.xml`
- [ ] Entender o papel do Spring Boot e da classe `CrudApplication`
- [ ] Entender arquitetura MVC (Model, View, Controller)
- [ ] Entender persistencia com JPA e MySQL
- [ ] Entender Thymeleaf e data binding em formulario/lista
- [ ] Entender fluxo completo: navegador -> controller -> repository -> banco -> view
- [ ] Entender o que ja esta pronto e o que ainda falta evoluir

## 1) Visao geral do que voce ja implementou

Voce construiu uma aplicacao web de cadastro de produtos com:

- Java 17
- Spring Boot
- Spring MVC
- Thymeleaf
- Spring Data JPA
- MySQL

CRUD implementado atualmente:

- **Create**: cadastrar produto
- **Read**: listar produtos
- **Delete**: excluir produto
- **Update**: ainda nao implementado

## 2) O que e cada tecnologia no seu projeto

### Maven (`pom.xml`)

Maven e a ferramenta de build/dependencias.

- Baixa bibliotecas automaticamente
- Compila o projeto
- Executa testes
- Gera o artefato final

No seu projeto, o arquivo principal disso e `pom.xml`.

### Spring Boot

Spring Boot facilita criar aplicacoes Spring sem configuracao manual pesada.

- Sobe servidor embutido
- Faz auto-configuracao
- Organiza o app por convencoes

Classe de entrada: `src/main/java/com/example/crud/CrudApplication.java`

### Spring MVC

Spring MVC controla requisicoes HTTP.

- Recebe URL no Controller
- Processa regra de negocio basica
- Retorna pagina (template) ou redirecionamento

Seu controller principal e `src/main/java/com/example/crud/controller/ProdutoController.java`.

### Thymeleaf

Thymeleaf e o motor de template HTML do lado do servidor.

- Gera HTML dinamico
- Usa atributos `th:*` como `th:each`, `th:text`, `th:field`

Arquivos:

- `src/main/resources/templates/formulario.html`
- `src/main/resources/templates/lista.html`

### JPA + Spring Data JPA

JPA mapeia classes Java para tabela no banco.

- Classe Java vira entidade (`@Entity`)
- Acesso a banco via repository (`JpaRepository`)
- Menos SQL manual no codigo

Arquivos:

- Entidade: `src/main/java/com/example/crud/model/Produto.java`
- Repository: `src/main/java/com/example/crud/repository/ProdutoRepository.java`

### MySQL

Banco relacional usado para persistir os produtos.

Configurado em `src/main/resources/application.properties`.

## 3) Estrutura do projeto explicada

```text
src/main/java/com/example/crud/
  CrudApplication.java          -> ponto de entrada da aplicacao
  controller/
    ProdutoController.java      -> recebe as rotas de produto
    WebController.java          -> rota extra (/gato)
  model/
    Produto.java                -> entidade/tabela produto
  repository/
    ProdutoRepository.java      -> acesso ao banco com JPA

src/main/resources/
  application.properties        -> configuracoes (db, jpa, etc.)
  static/
    index.html                  -> pagina inicial estatica
  templates/
    formulario.html             -> tela de cadastro
    lista.html                  -> tela de listagem

src/test/java/com/example/crud/
  CrudApplicationTests.java     -> teste basico de contexto
```

## 4) MVC no seu codigo (explicado como estudo)

### Model (`Produto`)

Arquivo: `src/main/java/com/example/crud/model/Produto.java`

O Model representa os dados e regras de estrutura da tabela.

Campos:

- `id`: identificador unico (chave primaria)
- `nome`: nome do produto (nao nulo e unico)
- `descricao`: descricao textual
- `preco`: preco do produto
- `quantidade`: quantidade em estoque
- `imagem`: URL/caminho da imagem

Anotacoes importantes:

- `@Entity`: diz que a classe e uma entidade JPA
- `@Id`: marca chave primaria
- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: id auto incremento
- `@Column(nullable = false, unique = true)`: restricoes da coluna

### Repository (`ProdutoRepository`)

Arquivo: `src/main/java/com/example/crud/repository/ProdutoRepository.java`

Ao estender `JpaRepository<Produto, Integer>`, voce ganha metodos prontos:

- `save(produto)`
- `findAll()`
- `deleteById(id)`

Ou seja: menos codigo repetitivo para CRUD.

### Controller (`ProdutoController`)

Arquivo: `src/main/java/com/example/crud/controller/ProdutoController.java`

Base da rota: `/produto`

Rotas e o que elas ensinam:

- `GET /produto/formulario`
  - prepara um objeto vazio no `Model`
  - abre a tela de cadastro
- `POST /produto/salvar`
  - recebe dados do formulario
  - salva no banco
  - redireciona para listagem
- `GET /produto/listar`
  - consulta todos os produtos
  - envia lista para a view
- `GET /produto/excluir/{id}`
  - exclui por id
  - redireciona para listagem

## 5) Views e bind de dados (Thymeleaf)

### `src/main/resources/static/index.html`

- Pagina inicial simples
- Link para iniciar o fluxo de cadastro

### `src/main/resources/templates/formulario.html`

Conceitos para estudar nesse arquivo:

- `th:object="${produto}"`: objeto do formulario
- `th:field="*{campo}"`: liga campo HTML com atributo Java
- `th:action="@{/produto/salvar}"`: destino do POST

Isso e o que chamamos de **data binding**.

### `src/main/resources/templates/lista.html`

Conceitos para estudar nesse arquivo:

- `th:each="p : ${produtos}"`: loop na lista
- `th:text="${p.nome}"`: imprime valor no HTML
- `th:href` com parametro de rota: gera URL de exclusao por id

## 6) Fluxo completo da aplicacao (fim a fim)

1. Usuario acessa `/`
2. Clica no link para formulario
3. Browser faz `GET /produto/formulario`
4. Controller retorna `formulario.html`
5. Usuario envia formulario (`POST /produto/salvar`)
6. Controller chama `produtoRepository.save(...)`
7. Dados vao para o MySQL
8. Controller redireciona para `/produto/listar`
9. Controller busca com `findAll()`
10. Thymeleaf renderiza `lista.html` com os produtos

## 7) Configuracoes de banco (o que cada linha faz)

Arquivo: `src/main/resources/application.properties`

- `spring.application.name=CRUD`
  - nome logico da aplicacao
- `spring.jpa.hibernate.ddl-auto=update`
  - atualiza estrutura do banco com base nas entidades
- `spring.datasource.url=...crud?createDatabaseIfNotExist=true`
  - URL de conexao e criacao do banco se nao existir
- `spring.datasource.username=root`
- `spring.datasource.password=admin`
  - credenciais do banco

Estudo importante:

- Em projeto real, evitar senha fixa em arquivo.

## 8) Como rodar

No Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

URL padrao:

- `http://localhost:8080/`

## 9) Testes (estado atual)

Arquivo: `src/test/java/com/example/crud/CrudApplicationTests.java`

- Teste atual: `contextLoads`
- Objetivo: validar que o contexto Spring sobe

Comando:

```powershell
.\mvnw.cmd test
```

Observacao conhecida da analise anterior:

- houve falha de encoding em resource (`MalformedInputException`) ao executar o build localmente.

## 10) O que revisar para prova/estudo

Prioridade alta:

1. Diferenca entre `@Controller`, `@GetMapping`, `@PostMapping`
2. Relacao entre `Model` (Spring MVC) e `th:object` (Thymeleaf)
3. Como `JpaRepository` entrega CRUD sem SQL manual
4. Como as anotacoes JPA viram estrutura de tabela
5. Fluxo de redirecionamento com `redirect:/...`

Prioridade media:

1. Validacoes com Bean Validation (`@NotBlank`, `@Positive`)
2. Tratamento de erros e excecoes
3. Diferenca entre pagina estatica (`static`) e template (`templates`)

## 11) O que ainda falta implementar (bom para continuar estudando)

1. **Update (editar produto)**
2. **Correcao do link/markup da coluna Editar em `lista.html`**
3. **Criar a view `gato.html` ou remover a rota `/gato`**
4. **Adicionar validacoes no formulario**
5. **Adicionar testes de controller e repository**
6. **Melhorar seguranca (Spring Security no futuro)**

## 12) Mini glossario rapido

- **CRUD**: Create, Read, Update, Delete
- **MVC**: Model, View, Controller
- **Entity**: classe mapeada para tabela
- **Repository**: camada de acesso a dados
- **Template Engine**: gera HTML dinamico no servidor
- **Data Binding**: ligar campo HTML a objeto Java

---

Se quiser, no proximo passo eu monto um **plano de estudo de 7 dias** usando exatamente esse projeto, com metas diarias e exercicios praticos em cada arquivo.

## 13) Exemplos de codigo explicados (linha de raciocinio)

Esta secao e para voce bater o olho no codigo e entender "o que esta acontecendo".

### Exemplo A - Classe principal (boot da aplicacao)

Arquivo: `src/main/java/com/example/crud/CrudApplication.java`

```java
@SpringBootApplication
public class CrudApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }
}
```

Explicacao:

- `@SpringBootApplication` junta configuracoes principais do Spring Boot.
- `main(...)` e o ponto de entrada Java.
- `SpringApplication.run(...)` sobe o contexto Spring e o servidor web.

Em resumo: sem essa classe, sua aplicacao nao inicia.

### Exemplo B - Controller com GET e POST

Arquivo: `src/main/java/com/example/crud/controller/ProdutoController.java`

```java
@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/formulario")
    public String exibirFormulario(Model model) {
        model.addAttribute("produto", new Produto());
        return "formulario";
    }

    @PostMapping("/salvar")
    public String salvarProdutos(Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/produto/listar";
    }
}
```

Explicacao:

- `@Controller`: classe que recebe requisicoes web e devolve view.
- `@RequestMapping("/produto")`: prefixo comum para as rotas da classe.
- `@GetMapping("/formulario")`: abre tela de cadastro.
- `Model model`: "mochila" de dados enviada para a pagina.
- `model.addAttribute("produto", new Produto())`: cria objeto para o formulario preencher.
- `@PostMapping("/salvar")`: recebe os dados enviados pelo formulario.
- `produtoRepository.save(produto)`: grava no banco.
- `redirect:/produto/listar`: evita reenvio de formulario (padrao PRG: Post/Redirect/Get).

### Exemplo C - Entidade JPA (mapeamento da tabela)

Arquivo: `src/main/java/com/example/crud/model/Produto.java`

```java
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String nome;

    private String descricao;
    private double preco;
    private int quantidade;
    private String imagem;
}
```

Explicacao:

- `@Entity`: marca classe que sera persistida no banco.
- `@Id`: chave primaria.
- `@GeneratedValue(IDENTITY)`: banco gera o id automaticamente.
- `@Column(nullable = false, unique = true)`: regra de negocio no nivel de banco para `nome`.

Em resumo: essa classe define "como e um produto" para o Java e para o banco.

### Exemplo D - Repository (CRUD sem SQL manual)

Arquivo: `src/main/java/com/example/crud/repository/ProdutoRepository.java`

```java
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
```

Explicacao:

- Ao estender `JpaRepository`, voce herda metodos prontos.
- Tipos informados:
  - `Produto` = entidade
  - `Integer` = tipo do id

Exemplos de metodos herdados (usados no projeto):

```java
produtoRepository.save(produto);
List<Produto> produtos = produtoRepository.findAll();
produtoRepository.deleteById(id);
```

### Exemplo E - Formulario com data binding (Thymeleaf)

Arquivo: `src/main/resources/templates/formulario.html`

```html
<form method="POST" th:action="@{/produto/salvar}" th:object="${produto}">
    <input type="text" th:field="*{nome}">
    <input type="text" th:field="*{descricao}">
    <input type="number" th:field="*{preco}">
    <input type="number" th:field="*{quantidade}">
    <input type="text" th:field="*{imagem}">
    <input type="submit" value="Cadastrar">
</form>
```

Explicacao:

- `th:object="${produto}"`: objeto base do formulario.
- `th:field="*{nome}"` (e outros): liga cada input a um atributo da classe `Produto`.
- Quando envia, Spring monta automaticamente um objeto `Produto` com os campos preenchidos.

### Exemplo F - Lista dinamica com `th:each`

Arquivo: `src/main/resources/templates/lista.html`

```html
<tr th:each="p : ${produtos}">
    <td th:text="${p.id}"></td>
    <td th:text="${p.nome}"></td>
    <td th:text="${p.descricao}"></td>
    <td th:text="${p.preco}"></td>
    <td th:text="${p.quantidade}"></td>
    <td><img th:src="${p.imagem}" alt="Imagem do produto"></td>
    <td><a th:href="@{/produto/excluir/{id}(id=${p.id})}">Excluir</a></td>
</tr>
```

Explicacao:

- `th:each`: repete a linha para cada produto.
- `th:text`: escreve valor no HTML.
- `th:href`: monta URL com parametro de rota (`id`).

### Exemplo G - Configuracao do banco

Arquivo: `src/main/resources/application.properties`

```properties
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/crud?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=admin
```

Explicacao:

- `ddl-auto=update`: tenta sincronizar estrutura das tabelas com as entidades.
- `datasource.url`: define host, porta, nome do banco e criacao automatica.
- `username/password`: credenciais de acesso.

### Exemplo H - Teste basico de contexto

Arquivo: `src/test/java/com/example/crud/CrudApplicationTests.java`

```java
@SpringBootTest
class CrudApplicationTests {

    @Test
    void contextLoads() {
    }
}
```

Explicacao:

- `@SpringBootTest`: sobe o contexto Spring para teste.
- `contextLoads()`: valida que a aplicacao inicia sem quebrar configuracao basica.

## 14) Mapa mental rapido (conceito -> arquivo -> resultado)

- `@SpringBootApplication` -> `CrudApplication.java` -> aplicacao inicia.
- `@Controller` + mappings -> `ProdutoController.java` -> rotas funcionam.
- `@Entity` -> `Produto.java` -> tabela/colunas mapeadas.
- `JpaRepository` -> `ProdutoRepository.java` -> CRUD pronto.
- `th:object` e `th:field` -> `formulario.html` -> formulario vira objeto Java.
- `th:each` e `th:text` -> `lista.html` -> dados aparecem na tabela.
- `application.properties` -> conexao com MySQL e comportamento do JPA.

## 15) Exercicios praticos (para fixar)

1. Adicione um campo `categoria` em `Produto` e mostre na `lista.html`.
2. Troque `double preco` por `BigDecimal` e pesquise por que isso e comum para valores monetarios.
3. Crie uma rota `GET /produto/buscar/{id}` e exiba um unico produto.
4. Implemente validacoes basicas (`nome` obrigatorio, `preco` maior que zero).
5. Implemente o Update completo (editar) e compare com o fluxo do Create.

