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
import com.geek.model.Client;
import com.geek.service.ClientService;

@Controller
@RequestMapping("/clients")
public class ClientController {
	
	protected static final String CLIENT_VIEW = "clients/showClients"; // view template for single clients
	protected static final String CLIENT_ADD_FORM_VIEW = "clients/newClients"; // form for new clients
	protected static final String CLIENT_EDIT_FORM_VIEW = "clients/editClients"; // form for editing an clients
	protected static final String CLIENT_PAGE_VIEW = "clients/allClients"; // list with pagination
	protected static final String INDEX_VIEW = "index"; // clients with pagination
	
	@Autowired
	private PageInitPagination pageInitiPagination;
	
	@Autowired
	private ClientService clientService;
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	
	public String getClientById(@PathVariable(value = "id")Long Id,Model model) {
		model.addAttribute("client", clientService.findById(Id));
		return CLIENT_VIEW;
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping
	public ModelAndView getAllClient(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = pageInitiPagination.initPaginationClient(pageSize, page, CLIENT_PAGE_VIEW);
		return modelAndView;
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/new")
	public String newClient(Model model) {
		if (!model.containsAttribute("client")) {
			model.addAttribute("client", new Client());
		}
		return CLIENT_ADD_FORM_VIEW;
	}
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/create")
	
	public String createClient(@Valid Client client, BindingResult result
			, Model model, RedirectAttributes attr) {
		
		if (result.hasErrors() || clientService.ClientValid(client) == false) {

			// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.article", result);
			attr.addFlashAttribute("client", client);

			attr.addFlashAttribute("error", "No se permite clientes"
					+ " con el mismo nombre");

			return "redirect:/clients/new";
		}
		Client newClient=clientService.create(client);
		model.addAttribute("client", newClient );
		
		return "redirect:/clients/" + newClient.getId();
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("{id}/edit")
	public String editCategory(@PathVariable(value = "id") Long Id, Model model) {
		/*
		 * in case of redirection from '/article/{id}/update' model will contain article
		 * with field values
		 */
		if (!model.containsAttribute("client")) {
			model.addAttribute("client", clientService.findById(Id));
		}
		return CLIENT_EDIT_FORM_VIEW;
	}
	
	
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping(path = "/{id}/update")
	public String updateClient(@PathVariable(value = "id") Long clientId, 
			@Valid Client clientDetails,
			BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() || clientService.ClientValid(clientDetails)==false) {

			/// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.client", result);
			attr.addFlashAttribute("client", clientDetails);

			attr.addFlashAttribute("error", "No se permite mismos clientes");

			return "redirect:/clients/" + clientDetails.getId() + "/edit";
		}
		
		clientService.update(clientId, clientDetails);
		model.addAttribute("client", clientService.findById(clientId));
		
		
		return "redirect:/clients/" + clientId;
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping(value = "/{id}/delete")
	public String deleteClient(@PathVariable("id") Long clientId) {
		
		clientService.delete(clientId);
		return "redirect:/clients";
	}
	

}