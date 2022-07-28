package models;

import entities.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class PessoaModel {
    public void create(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Pessoa criada com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao criar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        try{
            Pessoa pessoa = em.find(Pessoa.class, p.getId());
            em.getTransaction().begin();
            pessoa.setNome(p.getNome());
            pessoa.setEmail(p.getEmail());
            pessoa.setCpf(p.getCpf());
            pessoa.setDataNascimento(p.getDataNascimento());
            pessoa.setIdade(p.getIdade());
            em.getTransaction().commit();
        }catch (Exception e){
            System.err.println("Erro ao atualizar a pessoa!!!" + e.getMessage());
        }finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        try{
            Pessoa pessoa = em.find(Pessoa.class, p.getId());
            em.getTransaction().begin();
            em.remove(pessoa);
            em.getTransaction().commit();
        }catch (Exception e){
            System.err.println("Erro ao tentar deletar a pessoa !!!" + e.getMessage());
        }finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Pessoa findById(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Pessoa pessoa = null;
        try{
            pessoa = em.find(Pessoa.class, p.getId());
        }catch (Exception e){
            System.err.println("Erro ao tentar buscar a pessoa !!!" + e.getMessage());
        }finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
        return pessoa;
    }

    public List<Pessoa> findAll() {

        List<Pessoa> pessoas = new ArrayList<>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        try{
            pessoas = em.createQuery("FROM " + Pessoa.class.getName()).getResultList();
        }catch (Exception e){
            System.err.println("Erro ao tentar buscar a lista de pessoas !!!" + e.getMessage());
        }finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }

        return pessoas;
    }
}
