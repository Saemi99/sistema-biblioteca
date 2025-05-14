package com.example.modelo;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Aluno {

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matriculaAluno;

    private String nome;

    @OneToMany(mappedBy = "aluno")
    private List<Emprestimo> emprestimos;

    // Getters e Setters
    public int getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(int matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }
}
