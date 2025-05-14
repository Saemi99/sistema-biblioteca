package com.example.dao;

import com.example.modelo.Aluno;
import com.example.modelo.Emprestimo;
import com.example.modelo.Publicacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmprestimoDAOTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private EmprestimoDAO dao;


    @BeforeEach
    void setup() {
        emf = Persistence.createEntityManagerFactory("bibliotecaPU");
        em  = emf.createEntityManager();
        dao = new EmprestimoDAO(em);
    }

    @AfterEach
    void tearDown() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        em.close();
        emf.close();
    }


    private Aluno criarAluno() {
        Aluno a = new Aluno();
        a.setNome("Teste Aluno");
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
        return a;
    }

    private Publicacao criarPublicacao() {
        Publicacao p = new Publicacao();
        p.setTitulo("Livro Teste");
        p.setAno(2025);
        p.setAutor("Autor X");
        p.setTipo("Livro");
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        return p;
    }

    @Test
    void testSalvarEBuscarPorId() {
        Aluno a = criarAluno();
        Publicacao p = criarPublicacao();

        Emprestimo emp = new Emprestimo();
        emp.setAluno(a);
        emp.setPublicacao(p);
        emp.setDataEmprestimo(LocalDate.now());

        dao.salvar(emp);
        assertTrue(emp.getId() > 0, "ID deve ser gerado");

        Emprestimo buscado = dao.buscarPorId(emp.getId());
        assertNotNull(buscado);
        assertEquals(a.getMatriculaAluno(), buscado.getAluno().getMatriculaAluno());
    }

    @Test
    void testListarTodos() {
        Aluno a = criarAluno();
        Publicacao p = criarPublicacao();

        Emprestimo emp1 = new Emprestimo();
        emp1.setAluno(a);
        emp1.setPublicacao(p);
        emp1.setDataEmprestimo(LocalDate.now());
        dao.salvar(emp1);

        List<Emprestimo> todos = dao.listarTodos();
        assertTrue(todos.size() >= 1);
    }

    @Test
    void testAtualizar() {
        Aluno a = criarAluno();
        Publicacao p = criarPublicacao();

        Emprestimo emp = new Emprestimo();
        emp.setAluno(a);
        emp.setPublicacao(p);
        emp.setDataEmprestimo(LocalDate.now());
        dao.salvar(emp);

        LocalDate devolucao = LocalDate.now().plusDays(5);
        emp.setDataDevolucao(devolucao);
        dao.atualizar(emp);

        Emprestimo atualizado = dao.buscarPorId(emp.getId());
        assertEquals(devolucao, atualizado.getDataDevolucao());
    }

    @Test
    void testDeletar() {
        Aluno a = criarAluno();
        Publicacao p = criarPublicacao();

        Emprestimo emp = new Emprestimo();
        emp.setAluno(a);
        emp.setPublicacao(p);
        emp.setDataEmprestimo(LocalDate.now());
        dao.salvar(emp);

        dao.deletar(emp);

        Emprestimo excluido = dao.buscarPorId(emp.getId());
        assertNull(excluido);
    }
}
