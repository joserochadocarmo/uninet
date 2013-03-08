package br.com.uninet.service;

import java.util.List;

import br.com.uninet.entity.Curso;

public interface CursoService extends IService<Curso> {
	
	/**
	 * Retorna uma lista de Cursos pelo id da Universidade informado.
	 * 
	 * @param universidadeId
	 * @return List<Curso>
	 */
	List<Curso> findByUniversidadeId(Integer universidadeId);
}
