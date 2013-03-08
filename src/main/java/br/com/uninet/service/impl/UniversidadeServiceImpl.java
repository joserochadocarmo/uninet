package br.com.uninet.service.impl;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.uninet.dao.UniversidadeDAO;
import br.com.uninet.entity.Universidade;
import br.com.uninet.service.UniversidadeService;


@Service
public class UniversidadeServiceImpl implements UniversidadeService {
	
	@Inject
	protected UniversidadeDAO universidadeDAO;

	@Override
	@Transactional(readOnly = true)
	public Page<Universidade> findAll(int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(
				Direction.DESC, "nome"));
		Page<Universidade> universidades = universidadeDAO.findAll(pageable);
		return universidades;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Universidade> findByNomeLike(String nome, int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(
				Direction.DESC, "nome"));
		String q = "%" + nome + "%";
		Page<Universidade> universidades = universidadeDAO.findByNomeLike(q,
				pageable);
		return universidades;
	}

	@Override
	@Transactional(readOnly = true)
	public Universidade findById(Integer id) {
		Universidade universidade = universidadeDAO.findOne(id);
		return universidade;
	}

	@Override
	@Transactional
	public Universidade save(Universidade universidade) {
		return universidadeDAO.save(universidade);
	}

	@Override
	@Transactional
	public Universidade update(Universidade universidade) {
		return universidadeDAO.save(universidade);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		universidadeDAO.delete(id);
	}

}