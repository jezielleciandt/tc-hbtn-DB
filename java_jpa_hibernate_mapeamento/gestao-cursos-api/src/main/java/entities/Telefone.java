package entities;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class Telefone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String DDD;

    private String numero;
    @ManyToOne
    @JoinColumn(name = "aluno_id",referencedColumnName = "id", nullable = false)
    private Aluno aluno;

    public Telefone(String DDD, String numero, Aluno aluno) {
        this.DDD = DDD;
        this.numero = numero;
        this.aluno = aluno;
    }

    public Telefone() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDDD() {
        return DDD;
    }

    public void setDDD(String DDD) {
        this.DDD = DDD;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "id=" + id +
                ", DDD='" + DDD + '\'' +
                ", numero='" + numero + '\'' +
                ", aluno=" + aluno +
                '}';
    }
}
