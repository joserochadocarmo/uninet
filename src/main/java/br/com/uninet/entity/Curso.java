package br.com.uninet.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CURSOS")
public class Curso implements Serializable {
    
	private static final long serialVersionUID = 5934224091808353704L;

	@Id
    @Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOME")
    @Size(min = 1, max = 200)
    @NotNull
    private String nome;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="UNIVERSIDADE_ID",insertable=true, updatable=true)
    private Universidade universidade;
    
    @OneToMany(mappedBy="curso", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Aluno> alunos;

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Universidade getUniversidade() {
		return universidade;
	}

	public void setUniversidade(Universidade universidade) {
		this.universidade = universidade;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	@Override
    public String toString() {
        return "Curso [id=" + id + ", nome=" + nome + "]";
    }

}
