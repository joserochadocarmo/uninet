package br.com.uninet.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.uninet.entity.Curso;

public interface CursoDAO extends JpaRepository<Curso, Integer> {
	
	@Query("SELECT p FROM Curso p WHERE nome LIKE ?1")
    Page<Curso> findByNomeLike(String nome, Pageable pageable);
	
	@Query("SELECT p FROM Curso p WHERE p.universidade.id = ?1")
    List<Curso> findByUniversidadeId(Integer universidadeId);
	
}
