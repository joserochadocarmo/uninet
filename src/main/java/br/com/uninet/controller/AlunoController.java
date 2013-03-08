package br.com.uninet.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.uninet.entity.Aluno;
import br.com.uninet.entity.Curso;
import br.com.uninet.entity.Universidade;
import br.com.uninet.service.AlunoService;
import br.com.uninet.service.CursoService;
import br.com.uninet.service.UniversidadeService;

@Controller
@RequestMapping("/aluno")
public class AlunoController extends AbastractController {

	private static String LISTA = "/aluno/list";
	private static String SAVE_UPDATE = "/aluno/form";
	private static String DELETE = "/aluno/delete";

	@Inject
	protected AlunoService alunoService;

	@Inject
	protected UniversidadeService universidadeService;

	@Inject
	protected CursoService cursoService;

	@RequestMapping(value = "/list")
	public String list(
			@RequestParam(value = "page", required = false) Integer page,
			Model model) {

		Page<Aluno> paginas = alunoService.findAll(getPagina(page),
				PAGINA_LIMITE);

		model.addAttribute("paginas", paginas);

		return LISTA;
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public @ModelAttribute
	Aluno create(Model model) {
		getListaUniversidades(model);
		getListaCursos(model);

		Aluno aluno = new Aluno();
		aluno.setCurso(new Curso());
		aluno.getCurso().setUniversidade(new Universidade());
		return aluno;
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String save(@Valid Aluno aluno, BindingResult bindingResult,
			Model model) {
		getListaUniversidades(model);
		getListaCursos(model);

		if (aluno.getMatricula().isEmpty() == false) {
			this.validate(aluno, bindingResult);
		}
		
		this.validate(aluno, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAllAttributes(bindingResult.getModel());
			return SAVE_UPDATE;
		}

		alunoService.save(aluno);

		return "redirect:" + LISTA;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model) {
		getListaUniversidades(model);
		getListaCursos(model);

		Aluno aluno = alunoService.findById(id);

		model.addAttribute(aluno);

		return SAVE_UPDATE;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String update(@Valid Aluno aluno, BindingResult bindingResult,
			Model model) {
		getListaUniversidades(model);
		getListaCursos(model);

		if (aluno.getMatricula().isEmpty() == false) {
			this.validate(aluno, bindingResult);
		}

		if (bindingResult.hasErrors()) {
			model.addAllAttributes(bindingResult.getModel());
			return SAVE_UPDATE;
		}

		alunoService.update(aluno);

		return "redirect:" + LISTA;
	}

	@RequestMapping(value = "/excluir/{id}", method = RequestMethod.GET)
	public String excluir(@PathVariable("id") Integer id, Model model) {
		Aluno aluno = alunoService.findById(id);
		model.addAttribute(aluno);
		return DELETE;
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(
			@RequestParam(value = "page", required = false) Integer page,
			@PathVariable("id") Integer id) {

		alunoService.deleteById(id);

		return "redirect:" + LISTA;
	}

	/**
	 * Valida os campos obrigatorios da tela
	 */
	@Override
	public void validate(Object target, Errors errors) {
		Aluno aluno = (Aluno) target;

		if (aluno.getMatricula().isEmpty() == false) {
			boolean isMatriculado = alunoService.isMatriculado(aluno
					.getMatricula());
			if (isMatriculado) {
				errors.rejectValue("nome", "MSG04", null, null);
			}
		}

	}

	/**
	 * Retorna a lista de Universidades
	 * 
	 * @param model
	 */
	private void getListaUniversidades(Model model) {
		Page<Universidade> paginas = universidadeService.findAll(PAGINA_ATUAL,
				PAGINA_LIMITE);
		model.addAttribute("universidades", paginas.getContent());
	}

	/**
	 * Retorna a lista de Cursos de uma universidade
	 * 
	 * @param model
	 */
	private void getListaCursos(Model model) {
		Page<Curso> paginas = cursoService.findAll(PAGINA_ATUAL, PAGINA_LIMITE);
		model.addAttribute("cursos", paginas.getContent());

	}

}
