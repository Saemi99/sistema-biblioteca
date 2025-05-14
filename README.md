# Sistema de Biblioteca

---

## Identificação dos Alunos

* Evelin Saemi Monteiro Yoshida(202307907273)
* Vinícius Pinheiro Negrão(20200796344)

---

## Objetivo Geral da Atividade

Este projeto foi desenvolvido como parte da Avaliação 1 da disciplina de Aplicação de Banco de Dados. O objetivo é aplicar as regras de mapeamento objeto-relacional (ORM), por meio da API JPA (Jakarta Persistence API), para representar e manipular um modelo conceitual de um sistema de biblioteca.

Foram seguidos os seguintes requisitos propostos:

* Criar o mapeamento em SQL das tabelas com base no diagrama conceitual;
* Criar as classes de entidade Java usando anotações JPA;
* Criar uma classe DAO (Data Access Object) para a entidade Emprestimo;
* Criar uma classe de testes com operações básicas (inserção, alteração, exclusão e consulta);
* Publicar todo o código-fonte e documentação no GitHub, com identificação dos alunos e explicação dos pacotes/códigos neste arquivo README.md.

---

## Descrição Geral da Estrutura do Projeto

O projeto está organizado da seguinte forma, utilizando o padrão Maven:

* Pacote com.example.modelo: contém as classes de entidade mapeadas com JPA. São elas:

  * Aluno.java: representa um aluno que pode realizar empréstimos. Cada aluno possui um identificador (matriculaAluno) e um nome.
  * Publicacao.java: representa um livro, artigo ou outro tipo de publicação da biblioteca. Os atributos incluem codigoPub, titulo, ano, autor e tipo.
  * Emprestimo.java: representa a associação entre um Aluno e uma Publicacao, com as datas de empréstimo e devolução. Possui relacionamento ManyToOne com as classes Aluno e Publicacao.

* Pacote com.example.dao: contém a classe EmprestimoDAO.java, que implementa as operações básicas de persistência (inserir, atualizar, deletar, consultar) usando a EntityManager.

* Pacote de teste com.example.dao: contém a classe EmprestimoDAOTest.java, que realiza testes automatizados com JUnit. Cada teste verifica se as operações no DAO funcionam corretamente. O banco de dados utilizado nos testes é o H2 em modo memória, para que os dados sejam apagados a cada execução, garantindo testes limpos.

* Arquivo persistence.xml (localizado em src/main/resources/META-INF/): é o arquivo de configuração do JPA. Nele estão definidos:

  * O driver e URL do banco de dados H2;
  * Usuário e senha;
  * Dialeto do Hibernate;
  * Estratégia de criação e atualização de tabelas (hibernate.hbm2ddl.auto com valor update).

---

## Código SQL das Tabelas

As tabelas foram criadas com base no diagrama fornecido na atividade. O código SQL abaixo foi usado para gerar o modelo relacional:

CREATE TABLE ALUNO (
    matriculaAluno INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE PUBLICACAO (
    codigoPub INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    ano INT NOT NULL,
    autor VARCHAR(255) NOT NULL,
    tipo VARCHAR(100) NOT NULL
);

CREATE TABLE EMPRESTIMO (
    id INT PRIMARY KEY AUTO_INCREMENT,
    dataEmprestimo DATE NOT NULL,
    dataDevolucao DATE,
    aluno_id INT,
    publicacao_id INT,
    FOREIGN KEY (aluno_id) REFERENCES ALUNO(matriculaAluno),
    FOREIGN KEY (publicacao_id) REFERENCES PUBLICACAO(codigoPub)
);

Cada tabela representa uma entidade do modelo conceitual. As chaves estrangeiras foram utilizadas para associar os empréstimos aos alunos e às publicações.

---

## Explicações das Classes

### Aluno.java

Classe JPA com a anotação @Entity. Possui os atributos matriculaAluno (identificador único, chave primária) e nome. Um aluno pode ter vários empréstimos, mas a relação é unidirecional neste projeto, por simplicidade.

### Publicacao.java

Também uma classe JPA com os atributos: codigoPub (chave primária), titulo, ano, autor e tipo. Representa o item que será emprestado.

### Emprestimo.java

Classe que relaciona um aluno com uma publicação em uma determinada data. Contém id, dataEmprestimo, dataDevolucao, aluno (mapeado com @ManyToOne) e publicacao (também com @ManyToOne).

### EmprestimoDAO.java

Classe DAO responsável por intermediar o acesso ao banco de dados para a entidade Emprestimo. Implementa métodos como inserir, atualizar, remover e listarTodos.

### EmprestimoDAOTest.java

Classe de testes usando JUnit. Os testes cobrem:

* Inserção de novos empréstimos;
* Atualização de dados de devolução;
* Remoção de registros;
* Consulta e exibição dos empréstimos salvos.

Os testes garantem que as operações funcionam conforme esperado.

