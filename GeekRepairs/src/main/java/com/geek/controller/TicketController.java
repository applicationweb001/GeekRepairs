package com.geek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geek.model.entity.Client;
import com.geek.model.entity.Product;
import com.geek.model.entity.Ticket;
import com.geek.service.ClientService;
import com.geek.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@Autowired
	private ClientService clientService;

	@GetMapping
	public String showAllTickets(Model model) {
		model.addAttribute("tickets", ticketService.getAll());
		model.addAttribute("clients", clientService.getAll());
		return "tickets/list";
	}

	@GetMapping("/new")
	public String newTicketForm(Model model) {
		model.addAttribute("ticket", new Ticket());
		List<Client> clients = clientService.getAll();
		model.addAttribute("clients", clients);
		return "tickets/new";
	}

	@PostMapping("/save")
	public String saveNewTicket(Ticket ticket) {
		long id = ticketService.create(ticket);
		return "redirect:/tickets";
	}

	@GetMapping("/edit/{id}")
	public String editTicketForm(@PathVariable("id") long id, Model model) {
		Ticket ticket = ticketService.getOneById(id);
		List<Client> clients = clientService.getAll();
		model.addAttribute("clients", clients);
		model.addAttribute("ticket", ticket);
		return "tickets/edit";
	}

	@PostMapping("/update/{id}")
	public String updateTicket(@PathVariable("id") long id, Ticket ticket) {
		ticketService.update(id, ticket);
		return "redirect:/tickets";
	}

	@GetMapping("/delete/{id}")
	public String deleteTicket(@PathVariable("id") long id, Model model) {
		Ticket ticket = ticketService.getOneById(id);
		model.addAttribute("ticket", ticket);
		return "tickets/delete";
	}

	@PostMapping("/drop/{id}")
	public String dropticket(@PathVariable("id") long id) {
		ticketService.delete(id);
		return "redirect:/tickets";
	}

}
