package demo;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

import java.util.Date;
import java.util.List;

public class AdministrativoApp {
    public static void main(String[] args) {
        ProdutoModel produtoModel = new ProdutoModel();

        Produto p1 = new Produto();
        p1.setNome("TV");
        p1.setPreco(300.0);
        p1.setQuantidade(100);
        p1.setStatus(true);

        // 1) Criando um produto
        produtoModel.create(p1);
        Produto produto = produtoModel.findById(p1);
        System.out.println(produto);

        Produto p2 = new Produto("Geladeira", 180, 1900.0, true);
        produtoModel.create(p2);

        //2) Buscando todos os produtos na base de dados
        List<Produto> produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + produtos.size());


        PessoaModel pessoaModel = new PessoaModel();

        Pessoa novaPessoa = new Pessoa("João", "joao@email.com", 30, "1234521452", new Date());
        pessoaModel.create(novaPessoa);
        Pessoa pessoa1 = pessoaModel.findById(novaPessoa);
        System.out.println(pessoa1);

        List<Pessoa> pessoas = pessoaModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + pessoas.size());
    }
}
