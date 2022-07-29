package models;

import entities.Curso;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class CursoModel {
    EntityManagerFactory emf;
    EntityManager em;
    private void inicializacao(){
        emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        em = emf.createEntityManager();
    }
    public void create(Curso curso) {
        inicializacao();
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
            System.out.println("Curso criado com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao criar um curso !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Curso findById(Long id) {
        inicializacao();
        Curso curso = null;
        try {
            curso= em.find(Curso.class, id);
        }catch (Exception e){
            System.err.println("Erro ao tentar buscar o curso !!!" + e.getMessage());
        }finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
        return curso;
    }

    public List<Curso> findAll() {
        inicializacao();
        List<Curso> cursos = new ArrayList<>();
        try {
            cursos = em.createQuery("FROM " + Curso.class.getName()).getResultList();
        }catch (Exception e){
            System.err.println("Erro ao tentar buscar a lista de cursos !!!" + e.getMessage());
        }finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }

        return cursos;
    }

    public void update(Curso curso) {
        inicializacao();
        try{
           Curso curso1 = em.find(Curso.class, curso.getId());
            em.getTransaction().begin();
            curso1.setNome(curso.getNome());
            curso1.setSigla(curso.getSigla());
            curso1.setProfessor(curso.getProfessor());
            curso1.setAlunos(curso.getAlunos());
            em.getTransaction().commit();
        }catch (Exception e){
            System.err.println("Erro ao atualizar o curso !!!" + e.getMessage());
        }finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }

    }

    public void delete(Curso curso) {
        inicializacao();
        try{
            Curso curso1 = em.find(Curso.class, curso.getId());
            em.getTransaction().begin();
            em.remove(curso1);
            em.getTransaction().commit();
        }catch (Exception e){
            System.err.println("Erro ao tentar deletar o curso !!!" + e.getMessage());
        }finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }

    }
}
