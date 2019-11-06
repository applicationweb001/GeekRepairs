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
import com.geek.model.Client;
import com.geek.model.Ticket;
import com.geek.service.ClientService;
import com.geek.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {

	protected static final String TICKET_VIEW = "tickets/showTicket"; // view template for single Ticket
	protected static final String TICKET_ADD_FORM_VIEW = "tickets/newTicket"; // form for new Ticket
	protected static final String TICKET_EDIT_FORM_VIEW = "tickets/editTicket"; // form for editing an Ticket
	protected static final String TICKET_PAGE_VIEW = "tickets/allTickets"; // list with pagination
	protected static final String INDEX_VIEW = "index"; // tickets with pagination
	
	@Autowired
	private PageInitPagination pageInitiPagination;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private TicketService ticketService;
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public String getTicketById(@PathVariable(value = "id") Long ticketId, Model model) {
		model.addAttribute("ticket", ticketService.findById(ticketId));
		return TICKET_VIEW;
	}

	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping
	public ModelAndView getAllTickets(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = pageInitiPagination.initPaginationTicket(pageSize, page, TICKET_PAGE_VIEW);
		return modelAndView;
	}
	
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/new")
	public String newTicket(Model model) {

		// in case of redirection model will contain article
		if (!model.containsAttribute("ticket")) {
			model.addAttribute("ticket", new Ticket());
			List<Client> clients = clientService.getAll(); //se agrego esto
			model.addAttribute("clients",clients); // y esto
		}
		return TICKET_ADD_FORM_VIEW;
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping("/create")
	public String createTicket(@Valid Ticket ticket, BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors()) {

			// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.ticket", result);
			attr.addFlashAttribute("ticket", ticket);
			List<Client> clients = clientService.getAll(); //se agrego esto
			attr.addFlashAttribute("clients",clients); // y esto 1

			attr.addFlashAttribute("error", "No se permite articulos con el mismo titulo y autor");

			return "redirect:/tickets/new";
		}
		Ticket newTicket = ticketService.create(ticket);
		model.addAttribute("ticket", newTicket);

		return "redirect:/tickets/" + newTicket.getId();
	}

	@Secured({"ROLE_ADMIN"})
	@GetMapping("{id}/edit")
	public String editArticle(@PathVariable(value = "id") Long ticketId, Model model) {
		/*
		 * in case of redirection from '/article/{id}/update' model will contain article
		 * with field values
		 */
		if (!model.containsAttribute("ticket")) {
			model.addAttribute("ticket", ticketService.findById(ticketId));
			List<Client> clients = clientService.getAll(); //se agrego esto
			model.addAttribute("clients",clients); // y esto
		}
		return TICKET_EDIT_FORM_VIEW;
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping(path = "/{id}/update")
	public String updateArticle(@PathVariable(value = "id") Long ticketId, 
			@Valid Ticket ticketDetails,
			BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors()) {

			/// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.article", result);
			attr.addFlashAttribute("ticket", ticketDetails);
			List<Client> clients = clientService.getAll(); //se agrego esto
			attr.addFlashAttribute("clients",clients); // y esto 1

			attr.addFlashAttribute("error", "No se permite articulos con el mismo titulo y autor");

			return "redirect:/tickets/" + ticketDetails.getId() + "/edit";
		}

		ticketService.update(ticketId, ticketDetails);
		model.addAttribute("ticket", ticketService.findById(ticketId));
		return "redirect:/tickets/" + ticketId;
	}

	
	@Secured({"ROLE_ADMIN"})
	@GetMapping(value = "/{id}/delete")
	public String deleteTicket(@PathVariable("id") Long ticketId) {
		//Article article = articleService.findById(articleId);
		// String title = article.getTitle();
		ticketService.delete(ticketId);
		return "redirect:/tickets";
	}
	

	
}
