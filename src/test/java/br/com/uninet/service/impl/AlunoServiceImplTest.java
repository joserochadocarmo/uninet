package br.com.uninet.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.uninet.dao.AlunoDAO;
import br.com.uninet.entity.Aluno;
import br.com.uninet.entity.Curso;
import br.com.uninet.service.AlunoService;
import br.com.uninet.service.CursoService;


@ContextConfiguration(locations = "classpath:teste-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AlunoServiceImplTest {
	@Inject
	AlunoDAO alunoDAO;

	@Inject
	protected AlunoService alunoService;
	
	@Inject
	protected CursoService cursoService;

	/**
	 * Cadastra 20 alunos para teste.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		Curso curso = new Curso();
		curso.setNome("Analise de Sistemas - TURMA 1");
		cursoService.save(curso);
		for (int i = 1; i <= 20; i++) {
			Aluno aluno = new Aluno();
			aluno.setNome("Jos� Rocha do Carmo " + i);
			aluno.setMatricula("MAT-"+i);
			aluno.setCurso(curso);
			alunoDAO.save(aluno);
		}
		alunoDAO.flush();
	}

	/**
	 * Ao final de cada teste.
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {

	}

	// ---INICIO DOS TESTES--//
	@Test
	public void testFindAll() {
		Page<Aluno> paginas = alunoService.findAll(0, 5);

		assertNotNull(paginas);

		List<Aluno> alunos = paginas.getContent();

		assertNotNull(alunos);
		assertEquals(5, alunos.size());
	}

	@Test
	public void testFindByNomeLike() throws Exception {
		Page<Aluno> paginas = alunoService.findByNomeLike(
				"Jos� Rocha do Carmo", 0, 5);

		assertNotNull(paginas);

		List<Aluno> alunos = paginas.getContent();

		assertNotNull(alunos);
		assertEquals(5, alunos.size());
	}

	@Test
	public void testFindById() {
		Aluno aluno01 = alunoService.findAll(0, 1).getContent().get(0);
		Integer id = aluno01.getId();
		Aluno aluno = alunoService.findById(id);

		assertEquals(id, aluno.getId());
		assertEquals(aluno01.getNome(), aluno.getNome());
	}

	@Test
	public void testInsert() {

		Aluno aluno = new Aluno();
		aluno.setNome("Jos� Rocha do Carmo");
		aluno.setMatricula("MAT-01");
		
		Aluno result = alunoService.save(aluno);
		alunoDAO.flush();

		assertNotNull(result);
	}

	@Test
	public void testUpdate() {
		Aluno aluno01 = alunoService.findAll(0, 1).getContent().get(0);

		Aluno aluno = alunoService.findById(aluno01.getId());
		aluno.setNome("Jos� Rocha do Carmo XXL");
		alunoService.update(aluno);
		alunoDAO.flush();

		Aluno AlunoAlterado = alunoService.findById(aluno01.getId());
		assertEquals("Jos� Rocha do Carmo XXL", AlunoAlterado.getNome());
	}

	@Test
	public void testDeleteById() {
		Aluno aluno01 = alunoService.findAll(0, 1).getContent().get(0);

		alunoService.deleteById(aluno01.getId());
		alunoDAO.flush();
		Aluno AlunoExcluido = alunoService.findById(aluno01.getId());
		
		assertNull(AlunoExcluido);
	}

	@Test
	public void testIsMatriculado() {
		boolean isMatriculado = alunoService.isMatriculado("MAT-1");

		assertFalse(!isMatriculado );
	}

}
