package br.com.uninet.service.impl;

import static org.junit.Assert.assertEquals;
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

import br.com.uninet.dao.CursoDAO;
import br.com.uninet.entity.Curso;
import br.com.uninet.entity.Universidade;
import br.com.uninet.service.CursoService;
import br.com.uninet.service.UniversidadeService;


@ContextConfiguration(locations = "classpath:teste-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CursoServiceImplTest {
	@Inject
	CursoDAO cursoDAO;

	@Inject
	protected CursoService cursoService;
	
	@Inject
    protected UniversidadeService universidadeService;

	/**
	 * Cadastra 20 cursos para teste.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		Universidade universidade = new Universidade();
		universidade.setNome("UFG - Unidade 1");
		universidadeService.save(universidade);
		for (int i = 1; i <= 20; i++) {
			Curso curso = new Curso();
			curso.setNome("Analise de Sistemas - TURMA " + i);
			curso.setUniversidade(universidade);
			cursoDAO.save(curso);
		}
		cursoDAO.flush();
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
		Page<Curso> paginas = cursoService.findAll(0, 5);

		assertNotNull(paginas);

		List<Curso> cursos = paginas.getContent();

		assertNotNull(cursos);
		assertEquals(5, cursos.size());
	}

	@Test
	public void testFindByNomeLike() throws Exception {
		Page<Curso> paginas = cursoService.findByNomeLike(
				"Analise de Sistemas - TURMA", 0, 5);

		assertNotNull(paginas);

		List<Curso> cursos = paginas.getContent();

		assertNotNull(cursos);
		assertEquals(5, cursos.size());
	}

	@Test
	public void testFindById() {
		Curso curso01 = cursoService.findAll(0, 1).getContent().get(0);
		Integer id = curso01.getId();
		Curso curso = cursoService.findById(id);

		assertEquals(id, curso.getId());
		assertEquals(curso01.getNome(), curso.getNome());
	}

	@Test
	public void testInsert() {

		Curso curso = new Curso();
		curso.setNome("Analise de Sistemas - TURMA 1");

		Curso result = cursoService.save(curso);
		cursoDAO.flush();

		assertNotNull(result);
	}

	@Test
	public void testUpdate() {
		Curso curso01 = cursoService.findAll(0, 1).getContent().get(0);

		Curso curso = cursoService.findById(curso01.getId());
		curso.setNome("Analise de Sistemas - TURMA XXL");
		cursoService.update(curso);
		cursoDAO.flush();

		Curso CursoAlterado = cursoService.findById(curso01.getId());
		assertEquals("Analise de Sistemas - TURMA XXL", CursoAlterado.getNome());

	}

	@Test
	public void testDeleteById() {
		Curso curso01 = cursoService.findAll(0, 1).getContent().get(0);

		cursoService.deleteById(curso01.getId());
		cursoDAO.flush();
		Curso CursoExcluido = cursoService.findById(curso01.getId());
		assertNull(CursoExcluido);
	}

	@Test
	public void testFindByUniversidadeId() {
		Curso Curso01 = cursoService.findAll(0, 1).getContent().get(0);
		Integer idUniversidade = Curso01.getUniversidade().getId();

		List<Curso> cursos = cursoService.findByUniversidadeId(idUniversidade);

		assertNotNull(cursos);
		assertEquals(20, cursos.size());
	}

}
