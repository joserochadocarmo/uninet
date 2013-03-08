package br.com.uninet.service.impl;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.uninet.dao.AlunoDAO;
import br.com.uninet.entity.Aluno;
import br.com.uninet.service.AlunoService;


@Service
public class AlunoServiceImpl implements AlunoService {
	@Inject
	protected AlunoDAO alunoDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.mvc.service.IService#findAll(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Aluno> findAll(int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(
				Direction.DESC, "nome"));
		Page<Aluno> alunos = alunoDAO.findAll(pageable);
		return alunos;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Aluno> findByNomeLike(String nome, int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(
				Direction.DESC, "nome"));
		String q = "%" + nome + "%";
		Page<Aluno> alunos = alunoDAO.findByNomeLike(q, pageable);
		return alunos;
	}

	@Override
	@Transactional(readOnly = true)
	public Aluno findById(Integer id) {
		Aluno aluno = alunoDAO.findOne(id);
		return aluno;
	}

	@Override
	@Transactional
	public Aluno save(Aluno aluno) {
		return alunoDAO.save(aluno);
	}

	@Override
	@Transactional
	public Aluno update(Aluno aluno) {
		return alunoDAO.save(aluno);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		alunoDAO.delete(id);
	}

	@Override
	public boolean isMatriculado(String matricula) {
		final Aluno aluno = alunoDAO.isMatriculado(matricula);
		return aluno == null ? false : true;
	}

}