package com.example;

import com.example.dao.EmprestimoDAO;
import com.example.modelo.Emprestimo;
import com.example.modelo.Aluno;
import com.example.modelo.Publicacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;


public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecaPU");
        EntityManager em = emf.createEntityManager();

        EmprestimoDAO emprestimoDAO = new EmprestimoDAO(em);

        Aluno aluno = new Aluno();
        aluno.setMatriculaAluno(1);
        aluno.setNome("João");

        Publicacao publicacao = new Publicacao();
        publicacao.setCodigoPub(1);
        publicacao.setTitulo("Java Básico");
        publicacao.setAno(2023);
        publicacao.setAutor("Autor Exemplo");
        publicacao.setTipo("Livro");

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setAluno(aluno);
        emprestimo.setPublicacao(publicacao);
        emprestimo.setDataEmprestimo(LocalDate.now());

        // Salvar o empréstimo
        emprestimoDAO.salvar(emprestimo);

        em.close();
        emf.close();
    }
}
