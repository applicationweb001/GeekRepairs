package com.geek.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.geek.common.PageInitPagination;

import com.geek.model.Adviser;

import com.geek.service.AdviserService;

@Controller
@RequestMapping("/advisors")
public class AdviserController {

	protected static final String ADV_VIEW = "advisors/show"; // view template for single article
	protected static final String ADV_ADD_FORM_VIEW = "advisors/new"; // form for new article
	protected static final String ADV_EDIT_FORM_VIEW = "advisors/edit"; // form for editing an article
	protected static final String ADV_PAGE_VIEW = "advisors/list"; // list with pagination
	protected static final String INDEX_VIEW = "index";
	@Autowired
	private PageInitPagination pageInitPagination;
	@Autowired
	private AdviserService tecService;

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public String getTecById(@PathVariable(value = "id") Long articleId, Model model) {
		model.addAttribute("adviser", tecService.findById(articleId));
		return ADV_VIEW;
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping
	public ModelAndView getAllTec(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = pageInitPagination.initPaginationAdviser(pageSize, page, ADV_PAGE_VIEW);

		return modelAndView;
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/new")
	public String newTec(Model model) {

		// in case of redirection model will contain article
		if (!model.containsAttribute("adviser")) {
			model.addAttribute("adviser", new Adviser());
		}
		return ADV_ADD_FORM_VIEW;
	}

	@Secured({ "ROLE_ADMIN" })
	@PostMapping("/create")
	public String createArticle(@Valid Adviser article, BindingResult result, RedirectAttributes attr, Model model) {

		if (result.hasErrors() || tecService.AdviserIndValid(article) == false) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.adviser", result);
			attr.addFlashAttribute("adviser", article);

			attr.addFlashAttribute("error", "No se permite asesores" + " con el mismo nombre");

			return "redirect:/advisors/new";
		}
		Adviser newArticle = tecService.create(article);
		model.addAttribute("adviser", newArticle);

		return "redirect:/advisors/" + newArticle.getId();
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping("{id}/edit")
	public String editTec(@PathVariable(value = "id") Long articleId, Model model) {
		/*
		 * in case of redirection from '/article/{id}/update' model will contain article
		 * with field values
		 */
		if (!model.containsAttribute("adviser")) {
			model.addAttribute("adviser", tecService.findById(articleId));
		}
		return ADV_EDIT_FORM_VIEW;
	}

	@Secured({ "ROLE_ADMIN" })
	@PostMapping(path = "/{id}/update")
	public String updateTec(@PathVariable(value = "id") Long technicianIndId, @Valid Adviser technician,
			BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() || tecService.AdviserIndValid(technician) == false) {

			/// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.adviser", result);
			attr.addFlashAttribute("adviser", technician);

			attr.addFlashAttribute("error", "No se permite asesores con el mismo email");

			return "redirect:/advisors/" + technician.getId() + "/edit";
		}

		tecService.update(technicianIndId, technician);
		model.addAttribute("adviser", tecService.findById(technicianIndId));
		return "redirect:/advisors/" + technicianIndId;
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping(value = "/{id}/delete")
	public String deleteTec(@PathVariable("id") Long articleId) {
		tecService.delete(articleId);
		return "redirect:/advisors";
	}

}
