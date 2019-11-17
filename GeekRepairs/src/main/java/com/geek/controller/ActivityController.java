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
import com.geek.model.Activity;
import com.geek.model.Adviser;
import com.geek.service.ActivityService;
import com.geek.service.AdviserService;

@Controller
@RequestMapping("/activities")
public class ActivityController {

	protected static final String ACT_VIEW = "activities/show"; // view template for single article
	protected static final String ACT_ADD_FORM_VIEW = "activities/new"; // form for new article
	protected static final String ACT_EDIT_FORM_VIEW = "activities/edit"; // form for editing an article
	protected static final String ACT_PAGE_VIEW = "activities/list"; // list with pagination
	protected static final String INDEX_VIEW = "index";
	@Autowired
	private PageInitPagination pageInitPagination;
	@Autowired
	private ActivityService actService;

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public String getActById(@PathVariable(value = "id") Long articleId, Model model) {
		model.addAttribute("activity", actService.findById(articleId));
		return ACT_VIEW;
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping
	public ModelAndView getAllAct(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = pageInitPagination.initPaginationAdviser(pageSize, page, ACT_PAGE_VIEW);

		return modelAndView;
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/new")
	public String newAct(Model model) {

		// in case of redirection model will contain article
		if (!model.containsAttribute("activity")) {
			model.addAttribute("activity", new Activity());
		}
		return ACT_ADD_FORM_VIEW;
	}

	@Secured({ "ROLE_ADMIN" })
	@PostMapping("/create")
	public String createAct(@Valid Activity article, BindingResult result, RedirectAttributes attr, Model model) {

		if (result.hasErrors() || actService.ActivityInValid(article)== false) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.activity", result);
			attr.addFlashAttribute("activity", article);

			attr.addFlashAttribute("error", "No se permite actividades" + " con el mismo nombre");

			return "redirect:/activities/new";
		}
		Activity newAct= actService.create(article);
		model.addAttribute("activity", newAct);

		return "redirect:/activities/" + newAct.getId();
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping("{id}/edit")
	public String editAct(@PathVariable(value = "id") Long articleId, Model model) {
		/*
		 * in case of redirection from '/article/{id}/update' model will contain article
		 * with field values
		 */
		if (!model.containsAttribute("activity")) {
			model.addAttribute("activity", actService.findById(articleId));
		}
		return ACT_EDIT_FORM_VIEW;
	}

	@Secured({ "ROLE_ADMIN" })
	@PostMapping(path = "/{id}/update")
	public String updateAct(@PathVariable(value = "id") Long technicianIndId, @Valid Activity technician,
			BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() || actService.ActivityInValid(technician)== false) {

			/// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.activity", result);
			attr.addFlashAttribute("activity", technician);

			attr.addFlashAttribute("error", "No se permite actividades con el mismo nombre");

			return "redirect:/activities/" + technician.getId() + "/edit";
		}

		actService.update(technicianIndId, technician);
		model.addAttribute("activity", actService.findById(technicianIndId));
		return "redirect:/activities/" + technicianIndId;
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping(value = "/{id}/delete")
	public String deleteAct(@PathVariable("id") Long articleId) {
		actService.delete(articleId);
		return "redirect:/activities";
	}
	
}
