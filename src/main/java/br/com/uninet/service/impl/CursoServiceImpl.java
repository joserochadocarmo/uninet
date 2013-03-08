package br.com.uninet.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.uninet.dao.CursoDAO;
import br.com.uninet.entity.Curso;
import br.com.uninet.service.CursoService;


@Service
public class CursoServiceImpl implements CursoService {
	@Inject
	protected CursoDAO cursoDAO;

	@Override
	@Transactional(readOnly = true)
	public Page<Curso> findAll(int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(
				Direction.DESC, "nome"));
		Page<Curso> cursos = cursoDAO.findAll(pageable);
		return cursos;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Curso> findByNomeLike(String nome, int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(
				Direction.DESC, "nome"));
		String q = "%" + nome + "%";
		Page<Curso> cursos = cursoDAO.findByNomeLike(q, pageable);
		return cursos;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Curso> findByUniversidadeId(Integer universidadeId) {
		List<Curso> cursos = cursoDAO.findByUniversidadeId(universidadeId);
		return cursos;
	}

	@Override
	@Transactional(readOnly = true)
	public Curso findById(Integer id) {
		Curso curso = cursoDAO.findOne(id);
		return curso;
	}

	@Override
	@Transactional
	public Curso save(Curso curso) {
		curso.getUniversidade();
		return cursoDAO.save(curso);
	}

	@Override
	@Transactional
	public Curso update(Curso curso) {
		return cursoDAO.save(curso);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		cursoDAO.delete(id);
	}

}