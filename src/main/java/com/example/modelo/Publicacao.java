package com.example.modelo;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoPub;

    private String titulo;
    private int ano;
    private String autor;
    private String tipo;

    @OneToMany(mappedBy = "publicacao")
    private List<Emprestimo> emprestimos;

    // Getters e Setters
    public int getCodigoPub() {
        return codigoPub;
    }

    public void setCodigoPub(int codigoPub) {
        this.codigoPub = codigoPub;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }
}
