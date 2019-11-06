package com.geek.controller;

import java.util.List;
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
import com.geek.model.Category;
import com.geek.model.Specialty;
import com.geek.model.TechnicianInd;
import com.geek.service.SpecialtyService;
import com.geek.service.TechnicianIndService;

@Controller
@RequestMapping("/techniciansInd")
public class TechnicianIndController {
	protected static final String TECHNICIANIND_VIEW = "techniciansInd/showTechnicianInd"; // view template for single article
	protected static final String TECHNICIANIND_ADD_FORM_VIEW = "techniciansInd/newTechnicianInd"; // form for new article
	protected static final String TECHNICIANIND_EDIT_FORM_VIEW = "techniciansInd/editTechnicianInd"; // form for editing an article
	protected static final String TECHNICIANIND_PAGE_VIEW = "techniciansInd/allTechniciansInd"; // list with pagination
	protected static final String INDEX_VIEW = "index"; // articles with pagination
	
	
	@Autowired
	private PageInitPagination pageInitiPagination;
	
	@Autowired
	private TechnicianIndService technicianIndService;
	
	@Autowired
	private SpecialtyService specialtyService ;
	
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public String getTechnicianIndById(@PathVariable(value = "id") Long technicianIndId, Model model) {
		model.addAttribute("technicianInd", technicianIndService.findById(technicianIndId));
		return TECHNICIANIND_VIEW;
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping
	public ModelAndView getAllTechniciansInd(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = pageInitiPagination.initPaginationTechnicianInd(pageSize, page, TECHNICIANIND_PAGE_VIEW);
		
		
		return modelAndView;

	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/new")
	public String newTechniciansInd(Model model) {

		// in case of redirection model will contain article
		if (!model.containsAttribute("technicianInd")) {
			model.addAttribute("technicianInd", new TechnicianInd());
			List<Specialty> specialties = specialtyService.getAll(); //se agrego esto
			model.addAttribute("specialties",specialties); // y esto
		}
		return TECHNICIANIND_ADD_FORM_VIEW;
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping("/create")
	public String createTechnicianInd(@Valid TechnicianInd technicianInd, BindingResult result
			, Model model, RedirectAttributes attr) {

		if (result.hasErrors() || technicianIndService.TechnicianIndValid(technicianInd) == false) {

			// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.technicianInd", result);
			attr.addFlashAttribute("technicianInd", technicianInd);
			List<Specialty> specialties = specialtyService.getAll(); //se agrego esto
			attr.addFlashAttribute("specialties",specialties); // y esto

			attr.addFlashAttribute("error", "No se permite tecnicos"
					+ " con el mismo nombre");

			return "redirect:/techniciansInd/new";
		}
		TechnicianInd newTechnicianInd= technicianIndService.create(technicianInd);
		model.addAttribute("technicianInd", newTechnicianInd);

		return "redirect:/techniciansInd/" + newTechnicianInd.getId();
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("{id}/edit")
	public String editTechnicianInd(@PathVariable(value = "id") Long technicianIndId, Model model) {
		/*
		 * in case of redirection from '/article/{id}/update' model will contain article
		 * with field values
		 */
		if (!model.containsAttribute("technicianInd")) {
			List<Specialty> specialties = specialtyService.getAll(); //se agrego esto
			model.addAttribute("specialties",specialties); // y esto
			model.addAttribute("technicianInd", technicianIndService.findById(technicianIndId));
		}
		return TECHNICIANIND_EDIT_FORM_VIEW;
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping(path = "/{id}/update")
	public String updateTechnicianInd(@PathVariable(value = "id") Long technicianIndId, 
			@Valid TechnicianInd technicianIndDetails,
			BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() || technicianIndService.TechnicianIndValid(technicianIndDetails) == false) {

			/// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.technicianInd", result);
			attr.addFlashAttribute("technicianInd", technicianIndDetails);
			List<Specialty> specialties = specialtyService.getAll(); //se agrego esto
			attr.addFlashAttribute("specialties",specialties); // y esto

			attr.addFlashAttribute("error", "No se permite articulos con el mismo titulo y autor");

			return "redirect:/techniciansInd/" + technicianIndDetails.getId() + "/edit";
		}
		
		technicianIndService.update(technicianIndId, technicianIndDetails);
		model.addAttribute("technicianInd", technicianIndService.findById(technicianIndId));
		
		return "redirect:/techniciansInd/" + technicianIndId;
	}

	@Secured({"ROLE_ADMIN"})
	@GetMapping(value = "/{id}/delete")
	public String deleteTechnicianInd(@PathVariable("id") Long technicianIndId) {
		//Article article = articleService.findById(categoryId);
		// String title = article.getTitle();
		technicianIndService.delete(technicianIndId);
		return "redirect:/techniciansInd";
	}
	
	
}
