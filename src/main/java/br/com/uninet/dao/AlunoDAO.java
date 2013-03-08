package br.com.uninet.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.uninet.entity.Aluno;

public interface AlunoDAO extends JpaRepository<Aluno, Integer> {
	
	/**
	 * Retorna um lista de alunos pelo nome informado.
	 * @param nome
	 * @param pageable
	 * @return {@link Page}
	 */
	@Query("SELECT p FROM Aluno p WHERE nome LIKE ?1")
    Page<Aluno> findByNomeLike(String nome, Pageable pageable);
	
	/**
	 * Retorna um aluno pela matricula.
	 * @param matricula
	 * @return {@link Aluno}
	 */
	@Query("SELECT p FROM Aluno p WHERE matricula=?")
    Aluno isMatriculado(String matricula);
}
