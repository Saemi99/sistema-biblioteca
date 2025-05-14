package com.example.dao;

import com.example.modelo.Emprestimo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class EmprestimoDAO {

    private EntityManager em;

    public EmprestimoDAO(EntityManager em) {
        this.em = em;
    }

    public void salvar(Emprestimo emprestimo) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(emprestimo);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public void atualizar(Emprestimo emprestimo) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(emprestimo);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public void deletar(Emprestimo emprestimo) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(em.contains(emprestimo) ? emprestimo : em.merge(emprestimo));
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public Emprestimo buscarPorId(int id) {
        return em.find(Emprestimo.class, id);
    }

    public List<Emprestimo> listarTodos() {
        return em.createQuery("SELECT e FROM Emprestimo e", Emprestimo.class).getResultList();
    }
}
