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
import com.geek.model.Satisfaction;

import com.geek.model.Ticket;
import com.geek.service.SatisfactionService;
import com.geek.service.TicketService;

@Controller
@RequestMapping("/satisfactions")
public class SatisfactionController {
	protected static final String SATISFACTION_VIEW = "satisfactions/showSatisfaction"; // view template for single article
	protected static final String SATISFACTION_ADD_FORM_VIEW = "satisfactions/newSatisfaction"; // form for new article
	protected static final String SATISFACTION_EDIT_FORM_VIEW = "satisfactions/editSatisfaction"; // form for editing an article
	protected static final String SATISFACTION_PAGE_VIEW = "satisfactions/allSatisfactions"; // list with pagination
	protected static final String INDEX_VIEW = "index"; // articles with pagination
	
	
	@Autowired
	private PageInitPagination pageInitiPagination;
	
	@Autowired
	private SatisfactionService satisfactionService; 
	
	@Autowired
	private TicketService  ticketService;
	
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public String getSatisfactionById(@PathVariable(value = "id") Long SatisfactionId, Model model) {
		model.addAttribute("satisfaction", satisfactionService.findById(SatisfactionId));
		return SATISFACTION_VIEW;
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping
	public ModelAndView getAllSatisfaction(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = pageInitiPagination.initPaginationSatisfaction(pageSize, page, SATISFACTION_PAGE_VIEW);
		
		
		return modelAndView;
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("{id}/new")
	public String newSatisfaction( @PathVariable(value = "id")Long ticketId, Model model) {

		// in case of redirection model will contain article
		Satisfaction satisfaction=new Satisfaction();
		Ticket ticket = ticketService.findById(ticketId);
		satisfaction.setTicket(ticket);
		if (!model.containsAttribute("satisfaction")) {
			model.addAttribute("satisfaction",satisfaction);
			List<Ticket> Tickets = ticketService.getAll(); //se agrego esto
			model.addAttribute("tickets",Tickets); // y esto
		}
		return SATISFACTION_ADD_FORM_VIEW;
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("{id}/create")
	public String createSatisfaction(@PathVariable(value="id") Long ticketId, @Valid Satisfaction satisfaction, BindingResult result
			, Model model, RedirectAttributes attr) {

		if (result.hasErrors()) {

			// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.satisfaction", result);
			attr.addFlashAttribute("satisfaction", satisfaction);
			List<Ticket> tickets = ticketService.getAll(); //se agrego esto
			attr.addFlashAttribute("tickets",tickets); // y esto 1

			

			return "redirect:/satisfactions/new";
		}
		Ticket ticket=ticketService.findById(ticketId);
		satisfaction.setTicket(ticket);
		Satisfaction newSatisfaction= satisfactionService.create(satisfaction);
		model.addAttribute("satisfaction", newSatisfaction);

		return "redirect:/satisfactions/" + newSatisfaction.getId();
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("{id}/edit")
	public String editSatisfaction(@PathVariable(value = "id") Long satisfactionid, Model model) {
		/*
		 * in case of redirection from '/article/{id}/update' model will contain article
		 * with field values
		 */
		if (!model.containsAttribute("satisfaction")) {
			List<Ticket> tickets = ticketService.getAll(); //se agrego esto
			model.addAttribute("tickets",tickets); // y esto
			model.addAttribute("satisfaction", satisfactionService.findById(satisfactionid));
		}
		return SATISFACTION_EDIT_FORM_VIEW;
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping(path = "/{id}/update")
	public String updateSatisfaction(@PathVariable(value = "id") Long satisfactionId, 
			@Valid Satisfaction satisfactionDetails,
			BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() ) {

			/// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.satisfaction", result);
			attr.addFlashAttribute("satisfaction", satisfactionDetails);
			List<Ticket> tickets = ticketService.getAll(); //se agrego esto
			attr.addFlashAttribute("tickets",tickets); // y esto

			

			return "redirect:/satisfactions/" + satisfactionDetails.getId() + "/edit";
		}
		
		satisfactionService.update(satisfactionId, satisfactionDetails);
		model.addAttribute("satisfaction", satisfactionService.findById(satisfactionId));
		
		return "redirect:/satisfactions/" + satisfactionId;
	}

	@Secured({"ROLE_ADMIN"})
	@GetMapping(value = "/{id}/delete")
	public String deleteSatisfaction(@PathVariable("id") Long satisfactionId) {
		//Article article = articleService.findById(categoryId);
		// String title = article.getTitle();
		satisfactionService.delete(satisfactionId);
		return "redirect:/satisfactions";
	}
}
