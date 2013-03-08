package br.com.uninet.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.uninet.entity.Universidade;
import br.com.uninet.service.UniversidadeService;

@Controller
@RequestMapping("/universidade")
public class UniversidadeController extends AbastractController {

	private static String LISTA = "/universidade/list";
	private static String SAVE_UPDATE = "/universidade/form";
	private static String DELETE = "/universidade/delete";

	@Inject
	protected UniversidadeService universidadeService;

	@RequestMapping(value = "combo")
	public @ResponseBody List<Universidade> sectionList(
			Model model) {
		Page<Universidade> paginas = universidadeService.findAll(
				PAGINA_ATUAL, PAGINA_LIMITE);

		return paginas.getContent();
	}
	
	@RequestMapping(value = "/list")
	public String list(
			@RequestParam(value = "page", required = false) Integer page,
			Model model) {

		Page<Universidade> paginas = universidadeService.findAll(
				getPagina(page), PAGINA_LIMITE);

		model.addAttribute("paginas", paginas);

		return LISTA;
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public @ModelAttribute
	Universidade create(Model model) {
		Universidade universidade = new Universidade();
		return universidade;
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String save(@Valid Universidade universidade,
			BindingResult bindingResult, Model model) {

		// verifica se já existe Universidade cadastrada.
		if (universidade.getNome().isEmpty() == false) {
			this.validate(universidade, bindingResult);
		}

		if (bindingResult.hasErrors()) {
			model.addAllAttributes(bindingResult.getModel());
			return SAVE_UPDATE;
		}

		universidadeService.save(universidade);

		return "redirect:" + LISTA;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model) {
		Universidade universidade = universidadeService.findById(id);
		model.addAttribute(universidade);
		return SAVE_UPDATE;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String update(@Valid Universidade universidade,
			BindingResult bindingResult, Model model) {

		// verifica se já existe Universidade cadastrada.
		if (universidade.getNome().isEmpty() == false) {
			this.validate(universidade, bindingResult);
		}

		if (bindingResult.hasErrors()) {
			model.addAllAttributes(bindingResult.getModel());
			return SAVE_UPDATE;
		}

		universidadeService.update(universidade);

		return "redirect:" + LISTA;
	}

	@RequestMapping(value = "/excluir/{id}", method = RequestMethod.GET)
	public String excluir(@PathVariable("id") Integer id, Model model) {
		Universidade universidade = universidadeService.findById(id);
		model.addAttribute(universidade);
		return DELETE;
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(
			@RequestParam(value = "page", required = false) Integer page,
			@PathVariable("id") Integer id) {

		universidadeService.deleteById(id);

		return "redirect:" + LISTA;
	}

	/**
	 * Valida os campos obrigatorios da tela
	 */
	@Override
	public void validate(Object target, Errors errors) {
		Universidade universidade = (Universidade) target;

		Page<Universidade> paginas = universidadeService.findByNomeLike(
				universidade.getNome(), PAGINA_ATUAL, PAGINA_LIMITE);
		if (paginas != null && paginas.getNumberOfElements() > 0) {
			errors.rejectValue("nome", "MSG04", null, null);
		}
	}


}
