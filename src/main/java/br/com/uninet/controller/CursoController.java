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

import br.com.uninet.entity.Curso;
import br.com.uninet.entity.Universidade;
import br.com.uninet.service.CursoService;
import br.com.uninet.service.UniversidadeService;

@Controller
@RequestMapping("/curso")
public class CursoController extends AbastractController {

	private static String LISTA = "/curso/list";
	private static String SAVE_UPDATE = "/curso/form";
	private static String DELETE = "/curso/delete";

	@Inject
	protected CursoService cursoService;

	@Inject
	protected UniversidadeService universidadeService;

	@RequestMapping(value = "/list")
	public String list(
			@RequestParam(value = "page", required = false) Integer page,
			Model model) {

		Page<Curso> paginas = cursoService.findAll(getPagina(page),
				PAGINA_LIMITE);

		model.addAttribute("paginas", paginas);

		return LISTA;
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public @ModelAttribute
	Curso create(Model model) {
		getListaUniversidades(model);

		Curso curso = new Curso();
		curso.setUniversidade(new Universidade());
		return curso;
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String save(@Valid Curso curso, BindingResult bindingResult,
			Model model) {
		getListaUniversidades(model);

		// verifica se já existe Universidade cadastrada.
		if (curso.getUniversidade() == null
				|| curso.getUniversidade().getId() == null) {
			this.validate(curso, bindingResult);
		}

		if (bindingResult.hasErrors()) {
			model.addAllAttributes(bindingResult.getModel());
			return SAVE_UPDATE;
		}

		cursoService.save(curso);

		return "redirect:" + LISTA;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model) {
		getListaUniversidades(model);

		Curso curso = cursoService.findById(id);
		model.addAttribute(curso);

		return SAVE_UPDATE;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String update(@Valid Curso curso, BindingResult bindingResult,
			Model model) {
		getListaUniversidades(model);

		// verifica se já existe Universidade cadastrada.
		if (curso.getUniversidade() == null
				|| curso.getUniversidade().getId() == null) {
			this.validate(curso, bindingResult);
		}

		if (bindingResult.hasErrors()) {
			model.addAllAttributes(bindingResult.getModel());
			return SAVE_UPDATE;
		}

		cursoService.update(curso);

		return "redirect:" + LISTA;
	}

	@RequestMapping(value = "/excluir/{id}", method = RequestMethod.GET)
	public String excluir(@PathVariable("id") Integer id, Model model) {
		Curso curso = cursoService.findById(id);
		model.addAttribute(curso);
		return DELETE;
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(
			@RequestParam(value = "page", required = false) Integer page,
			@PathVariable("id") Integer id) {

		cursoService.deleteById(id);

		return "redirect:" + LISTA;
	}

	/**
	 * Valida os campos obrigatorios da tela
	 */
	@Override
	public void validate(Object target, Errors errors) {
		errors.rejectValue("universidade.id", "MSG05", null, null);

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

}
