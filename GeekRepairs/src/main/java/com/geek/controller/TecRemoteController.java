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

import com.geek.model.TecRemote;

import com.geek.service.TecRemoteService;

@Controller
@RequestMapping("/tecsremote")
public class TecRemoteController {

	protected static final String TECR_VIEW = "tecsremote/show"; // view template for single article
	protected static final String TECR_ADD_FORM_VIEW = "tecsremote/new"; // form for new article
	protected static final String TECR_EDIT_FORM_VIEW = "tecsremote/edit"; // form for editing an article
	protected static final String TECR_PAGE_VIEW = "tecsremote/list"; // list with pagination
	protected static final String INDEX_VIEW = "index";
	@Autowired
	private PageInitPagination pageInitPagination;
	@Autowired
	private TecRemoteService tecService;

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public String getTecById(@PathVariable(value = "id") Long articleId, Model model) {
		model.addAttribute("tecRemote", tecService.findById(articleId));
		return TECR_VIEW;
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping
	public ModelAndView getAllTec(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = pageInitPagination.initPaginationTecRemote(pageSize, page, TECR_PAGE_VIEW);

		return modelAndView;
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/new")
	public String newTec(Model model) {

		// in case of redirection model will contain article
		if (!model.containsAttribute("tecRemote")) {
			model.addAttribute("tecRemote", new TecRemote());
		}
		return TECR_ADD_FORM_VIEW;
	}

	@Secured({ "ROLE_ADMIN" })
	@PostMapping("/create")
	public String createArticle(@Valid TecRemote article, BindingResult result, RedirectAttributes attr, Model model) {

		if (result.hasErrors() || tecService.TechnicianIndValid(article) == false) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.tecRemote", result);
			attr.addFlashAttribute("tecRemote", article);

			attr.addFlashAttribute("error", "No se permite tecnicos" + " con el mismo nombre");

			return "redirect:/tecsremote/new";
		}
		TecRemote newArticle = tecService.create(article);
		model.addAttribute("tecRemote", newArticle);

		return "redirect:/tecsremote/" + newArticle.getId();
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping("{id}/edit")
	public String editTec(@PathVariable(value = "id") Long articleId, Model model) {
		/*
		 * in case of redirection from '/article/{id}/update' model will contain article
		 * with field values
		 */
		if (!model.containsAttribute("tecRemote")) {
			model.addAttribute("tecRemote", tecService.findById(articleId));
		}
		return TECR_EDIT_FORM_VIEW;
	}

	@Secured({ "ROLE_ADMIN" })
	@PostMapping(path = "/{id}/update")
	public String updateTec(@PathVariable(value = "id") Long technicianIndId, @Valid TecRemote technician,
			BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() || tecService.TechnicianIndValid(technician) == false) {

			/// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.tecRemote", result);
			attr.addFlashAttribute("tecRemote", technician);

			attr.addFlashAttribute("error", "No se permite tecnicos con el mismo email");

			return "redirect:/tecsremote/" + technician.getId() + "/edit";
		}

		tecService.update(technicianIndId, technician);
		model.addAttribute("tecRemote", tecService.findById(technicianIndId));
		return "redirect:/tecsremote/" + technicianIndId;
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping(value = "/{id}/delete")
	public String deleteTec(@PathVariable("id") Long articleId) {
		tecService.delete(articleId);
		return "redirect:/tecsremote";
	}

}
