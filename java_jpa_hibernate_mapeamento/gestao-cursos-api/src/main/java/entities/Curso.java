package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sigla;
    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Alunos_Cursos", joinColumns = @JoinColumn (name = "curso_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id", referencedColumnName = "id"))
    private List<Aluno> alunos;

//    @OneToOne(mappedBy = "curso_id", cascade = CascadeType.ALL)
//    private MaterialCurso materialCurso;

    public Curso(String nome, String sigla, Professor professor, List<Aluno> alunos) {
        this.nome = nome;
        this.sigla = sigla;
        this.professor = professor;
        this.alunos = alunos;

    }

    public Curso() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                ", professor=" + professor +
                ", alunos=" + alunos +
                '}';
    }
}
