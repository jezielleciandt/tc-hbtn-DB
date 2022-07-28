package models;

import entities.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ProdutoModel {
    public void create(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Produto criado com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao criar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        try{
            Produto produto = em.find(Produto.class,p.getId());
            em.getTransaction().begin();
            produto.setNome(p.getNome());
            produto.setPreco(p.getPreco());
            produto.setQuantidade(p.getQuantidade());
            produto.setStatus(p.getStatus());
            em.getTransaction().commit();
        }catch (Exception e){
            System.err.println("Erro ao atualizar o produto !!!" + e.getMessage());
        }finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }

    }

    public void delete(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        try{
            Produto produto = em.find(Produto.class,p.getId());
            em.getTransaction().begin();
            em.remove(produto);
            em.getTransaction().commit();
        }catch (Exception e){
            System.err.println("Erro ao tentar deletar o produto !!!" + e.getMessage());
        }finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Produto findById(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Produto produto = null;
        try {
            produto = em.find(Produto.class, p.getId());
        }catch (Exception e){
            System.err.println("Erro ao tentar buscar a produto !!!" + e.getMessage());
        }finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
        return produto;
    }

    public List<Produto> findAll() {

        List<Produto> produtos = new ArrayList<>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            produtos = em.createQuery("FROM " + Produto.class.getName()).getResultList();
        }catch (Exception e){
            System.err.println("Erro ao tentar buscar a lista de produtos !!!" + e.getMessage());
        }finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
        return produtos;
    }
}
