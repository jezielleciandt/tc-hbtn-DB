package models;

import entities.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class AlunoModel {
    EntityManagerFactory emf;
    EntityManager em;
    private void inicializacao(){
        emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        em = emf.createEntityManager();
    }
    public void create(Aluno aluno) {
            inicializacao();
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno criado com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao criar um aluno !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Aluno findById(Long id) {
        inicializacao();
        Aluno aluno = null;
        try {
            aluno= em.find(Aluno.class, id);
        }catch (Exception e){
            System.err.println("Erro ao tentar buscar o aluno !!!" + e.getMessage());
        }finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
        return aluno;
    }

    public List<Aluno> findAll() {
       inicializacao();
       List<Aluno> alunos = new ArrayList<>();
        try {
            alunos = em.createQuery("FROM " + Aluno.class.getName()).getResultList();
        }catch (Exception e){
            System.err.println("Erro ao tentar buscar a lista de alunos !!!" + e.getMessage());
        }finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }

        return alunos;
    }

    public void update(Aluno aluno) {
        inicializacao();
        try{
            Aluno aluno1 = em.find(Aluno.class, aluno.getId());
            em.getTransaction().begin();
            aluno1.setNomeCompleto(aluno.getNomeCompleto());
            aluno1.setEmail(aluno.getEmail());
            aluno1.setMatricula(aluno.getMatricula());
            aluno1.setNascimento(aluno.getNascimento());
            em.getTransaction().commit();
        }catch (Exception e){
            System.err.println("Erro ao atualizar o aluno !!!" + e.getMessage());
        }finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }

    }

    public void delete(Aluno aluno) {
        inicializacao();
        try{
            Aluno aluno1 = em.find(Aluno.class, aluno.getId());
            em.getTransaction().begin();
            em.remove(aluno1);
            em.getTransaction().commit();
        }catch (Exception e){
            System.err.println("Erro ao tentar deletar o aluno !!!" + e.getMessage());
        }finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }

    }
}
