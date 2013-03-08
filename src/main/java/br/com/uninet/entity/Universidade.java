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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "UNIVERSIDADES")
public class Universidade implements Serializable {
    
	private static final long serialVersionUID = 2696020079107223916L;

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOME")
    @Size(min = 1, max = 200)
    @NotNull
    private String nome;
    
    @OneToMany(mappedBy="universidade", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Curso> Cursos;

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


	public List<Curso> getCursos() {
		return Cursos;
	}

	public void setCursos(List<Curso> cursos) {
		Cursos = cursos;
	}
	
	@Override
	public String toString() {
		return "Universidade [id=" + id + ", nome=" + nome + "]";
	}
}
