package br.com.uninet.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.uninet.entity.Universidade;

public interface UniversidadeDAO extends JpaRepository<Universidade, Integer> {
	
	@Query("SELECT p FROM Universidade p WHERE upper(nome) LIKE upper(?1)")
    Page<Universidade> findByNomeLike(String nome, Pageable pageable);
}
