package demo;

import entities.Aluno;
import entities.Curso;
import entities.Professor;
import models.AlunoModel;
import models.CursoModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestaoCursosMain {
    public static void main(String[] args) {
        AlunoModel alunoModel = new AlunoModel();

        Aluno aluno1 = new Aluno("Maria Joaquina","12345",new Date(),"maria@email.com");

        Aluno aluno2 = new Aluno("Alice da Silva","12356",new Date(),"alice@email.com");

        Aluno aluno3 = new Aluno("Joaquim da Luz","12367",new Date(),"joaquim@email.com");

        Aluno aluno4 = new Aluno("José Maria","12378",new Date(),"jose@email.com");

        List<Aluno> alunos = new ArrayList<>();
        alunos.add(aluno1);
        alunos.add(aluno2);
        alunos.add(aluno3);
        alunos.add(aluno4);

        for(Aluno aluno: alunos){
            alunoModel.create(aluno);
        }

        List<Aluno> alunosRetornados = new ArrayList<>();
        alunosRetornados = alunoModel.findAll();

        for (Aluno aluno: alunosRetornados){
            System.out.println(aluno);
        }
        CursoModel cursoModel = new CursoModel();
        Professor professor = new Professor("João Carlos", "563421","joao@email.com");
        Curso curso = new Curso("Programação", "POO", professor,alunos);
        cursoModel.create(curso);
        Curso cursoProgramação = cursoModel.findById(1L);
        System.out.println(cursoProgramação);

    }
}
