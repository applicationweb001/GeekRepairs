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

import com.geek.model.Problem;

import com.geek.service.ProblemService;


@Controller
@RequestMapping("/problems")
public class ProblemController {

	protected static final String PRO_VIEW = "problems/show"; // view template for single article
	protected static final String PRO_ADD_FORM_VIEW = "problems/new"; // form for new article
	protected static final String PRO_EDIT_FORM_VIEW = "problems/edit"; // form for editing an article
	protected static final String PRO_PAGE_VIEW = "problems/list"; // list with pagination
	protected static final String INDEX_VIEW = "index"; // articles with pagination

	@Autowired
	private PageInitPagination pageInitiPagination;
	
	@Autowired
	private ProblemService problemService;
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public String getProblemById(@PathVariable(value = "id") Long id, Model model) {
		model.addAttribute("problem", problemService.findById(id));
		return PRO_VIEW;
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping
	public ModelAndView getAll(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = pageInitiPagination.initPaginationProblem(pageSize, page, PRO_PAGE_VIEW);
		return modelAndView;

	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/new")
	public String newProblem(Model model) {

		// in case of redirection model will contain article
		if (!model.containsAttribute("problem")) {
			model.addAttribute("problem", new Problem());
		}
		return PRO_ADD_FORM_VIEW;
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping("/create")
	public String createProblem(@Valid Problem prob, BindingResult result
			, Model model, RedirectAttributes attr) {

		if (result.hasErrors() || problemService.ProblemValid(prob) == false) {

			// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.problem", result);
			attr.addFlashAttribute("problem", prob);

			attr.addFlashAttribute("error", "No se permite problemas"
					+ " con el mismo nombre");

			return "redirect:/problems/new";
		}
		Problem newProb= problemService.create(prob);
		model.addAttribute("problem", newProb);

		return "redirect:/problems/" + newProb.getId();
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("{id}/edit")
	public String editProb(@PathVariable(value = "id") Long id, Model model) {
		/*
		 * in case of redirection from '/article/{id}/update' model will contain article
		 * with field values
		 */
		if (!model.containsAttribute("problem")) {
			model.addAttribute("problem", problemService.findById(id));
		}
		return PRO_EDIT_FORM_VIEW;
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping(path = "/{id}/update")
	public String updateProblem(@PathVariable(value = "id") Long id, 
			@Valid Problem prob,
			BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() || problemService.ProblemValid(prob)== false) {

			/// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.problem", result);
			attr.addFlashAttribute("problem", prob);

			attr.addFlashAttribute("error", "No se permite cambiar el titulo o nombre");

			return "redirect:/problems/" + prob.getId() + "/edit";
		}
		
		problemService.update(id, prob);
		model.addAttribute("problem", problemService.findById(id));
		return "redirect:/problems/" + id;
	}

	@Secured({"ROLE_ADMIN"})
	@GetMapping(value = "/{id}/delete")
	public String deleteProblem(@PathVariable("id") Long id) {
		
		problemService.delete(id);
		return "redirect:/problems";
	}
	
	
}
