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
import com.geek.model.Specialty;
import com.geek.service.SpecialtyService;

@Controller
@RequestMapping("/specialties")
public class SpecialtyController {

	protected static final String SPECIALTY_VIEW = "specialties/showSpecialty"; // view template for single article
	protected static final String SPECIALTY_ADD_FORM_VIEW = "specialties/newSpecialty"; // form for new article
	protected static final String SPECIALTY_EDIT_FORM_VIEW = "specialties/editSpecialty"; // form for editing an article
	protected static final String SPECIALTY_PAGE_VIEW = "specialties/allSpecialties"; // list with pagination
	protected static final String INDEX_VIEW = "index"; // articles with pagination

	@Autowired
	private PageInitPagination pageInitiPagination;
	
	@Autowired
	private SpecialtyService specialtyService;
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public String getSpecialtyById(@PathVariable(value = "id") Long specialtyId, Model model) {
		model.addAttribute("specialty", specialtyService.findById(specialtyId));
		return SPECIALTY_VIEW;
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping
	public ModelAndView getAllSpecialties(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = pageInitiPagination.initPaginationSpecialty(pageSize, page, SPECIALTY_PAGE_VIEW);
		return modelAndView;

	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/new")
	public String newSpecialty(Model model) {

		// in case of redirection model will contain article
		if (!model.containsAttribute("specialty")) {
			model.addAttribute("specialty", new Specialty());
		}
		return SPECIALTY_ADD_FORM_VIEW;
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping("/create")
	public String createSpecialty(@Valid Specialty specialty, BindingResult result
			, Model model, RedirectAttributes attr) {

		if (result.hasErrors() || specialtyService.SpecialtyValid(specialty) == false) {

			// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.article", result);
			attr.addFlashAttribute("specialty", specialty);

			attr.addFlashAttribute("error", "No se permite especialidades"
					+ " con el mismo nombre");

			return "redirect:/specialties/new";
		}
		Specialty newSpecialty= specialtyService.create(specialty);
		model.addAttribute("specialty", newSpecialty);

		return "redirect:/specialties/" + newSpecialty.getId();
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("{id}/edit")
	public String editSpecialty(@PathVariable(value = "id") Long specialtyId, Model model) {
		/*
		 * in case of redirection from '/article/{id}/update' model will contain article
		 * with field values
		 */
		if (!model.containsAttribute("specialty")) {
			model.addAttribute("specialty", specialtyService.findById(specialtyId));
		}
		return SPECIALTY_EDIT_FORM_VIEW;
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping(path = "/{id}/update")
	public String updateSpecialty(@PathVariable(value = "id") Long specialtyId, 
			@Valid Specialty specialtyDetails,
			BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() || specialtyService.SpecialtyValid(specialtyDetails) == false) {

			/// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.specialty", result);
			attr.addFlashAttribute("specialty", specialtyDetails);

			attr.addFlashAttribute("error", "No se permite el mismo nombre para la especialidad");

			return "redirect:/specialties/" + specialtyDetails.getId() + "/edit";
		}
		
		specialtyService.update(specialtyId, specialtyDetails);
		model.addAttribute("specialty", specialtyService.findById(specialtyId));
		return "redirect:/specialties/" + specialtyId;
	}

	@Secured({"ROLE_ADMIN"})
	@GetMapping(value = "/{id}/delete")
	public String deleteSpecialty(@PathVariable("id") Long specialtyId) {
		//Article article = articleService.findById(categoryId);
		// String title = article.getTitle();
		specialtyService.delete(specialtyId);
		return "redirect:/specialties";
	}
	
}
