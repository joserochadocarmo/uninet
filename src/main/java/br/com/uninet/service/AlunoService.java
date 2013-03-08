package br.com.uninet.service;

import br.com.uninet.entity.Aluno;

public interface AlunoService extends IService<Aluno> {
	
	boolean isMatriculado(final String matricula);
	
}
