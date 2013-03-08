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

import br.com.uninet.dao.UniversidadeDAO;
import br.com.uninet.entity.Universidade;
import br.com.uninet.service.UniversidadeService;


@ContextConfiguration(locations = "classpath:teste-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UniversidadeServiceImplTest {
	@Inject
	UniversidadeDAO universidadeDAO;

	@Inject
    protected UniversidadeService universidadeService;

	/**
	 * Cadastra 20 Universidades para teste.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		for (int i = 1; i <= 20; i++) {
			Universidade universidade = new Universidade();
			universidade.setNome("UFG - Unidade " + i);
			universidadeDAO.save(universidade);
		}
		universidadeDAO.flush();
	}

	/**
	 * Ao final de cada teste.
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		
	}

	//---INICIO DOS TESTES--//
	@Test
	public void testFindAll() {
		Page<Universidade> paginas = universidadeService.findAll(0, 5);

		assertNotNull(paginas);

		List<Universidade> universidades = paginas.getContent();

		assertNotNull(universidades);
		assertEquals(5, universidades.size());
	}

	@Test
	public void testFindByNomeLike() throws Exception {
		Page<Universidade> paginas = universidadeService.findByNomeLike(
				"UFG - Unidade", 0, 5);

		assertNotNull(paginas);

		List<Universidade> universidades = paginas.getContent();

		assertNotNull(universidades);
		assertEquals(5, universidades.size());
	}

	@Test
	public void testFindById() {
		Universidade universidade01 = universidadeService.findAll(0, 1)
				.getContent().get(0);
		Integer id = universidade01.getId();
		Universidade universidade = universidadeService.findById(id);

		assertEquals(id, universidade.getId());
		assertEquals(universidade01.getNome(), universidade.getNome());
	}

	@Test
	public void testInsert() {

		Universidade universidade = new Universidade();
		universidade.setNome("UFG - Unidade Goi�nia");

		Universidade result = universidadeService.save(universidade);
		universidadeDAO.flush();

		assertNotNull(result);
	}

	@Test
	public void testUpdate() {
		Universidade universidade01 = universidadeService.findAll(0, 1)
				.getContent().get(0);

		Universidade universidade = universidadeService.findById(universidade01
				.getId());
		universidade.setNome("UEG - UNIDADE ESEFFEGO");
		universidadeService.update(universidade);
		universidadeDAO.flush();

		Universidade universidadeAlterada = universidadeService
				.findById(universidade01.getId());
		assertEquals("UEG - UNIDADE ESEFFEGO", universidadeAlterada.getNome());

	}

	@Test
	public void testDeleteById() {
		Universidade universidade01 = universidadeService.findAll(0, 1)
				.getContent().get(0);

		universidadeService.deleteById(universidade01.getId());
		universidadeDAO.flush();
		Universidade universidadeExcluida = universidadeService
				.findById(universidade01.getId());
		assertNull(universidadeExcluida);
	}

}
