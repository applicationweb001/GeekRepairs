package com.geek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geek.model.entity.Client;
import com.geek.service.ClientService;

@Controller
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public String showAllClients(Model model) {
		model.addAttribute("clients", clientService.getAll());
		return "clients/list";
	}
	
	@GetMapping("/new")
	public String newClientForm (Model model) {
		model.addAttribute("client", new Client());
		return "clients/new";
		
	}
	
	@PostMapping("/save")
	public String saveNewClient(Client client) {
		long id=clientService.create(client);
		return "redirect:/client";
	}
	
	@GetMapping("/edit/{id}")
	public String editClientForm(@PathVariable("id")long id, Model model) {
		Client client= clientService.getOneById(id);
		System.out.println(client.getName());
		model.addAttribute("client", client);
		return "clients/edit";
		
		
	}
	@PostMapping("update/{id}")
	public String updateClient(@PathVariable("id")long id, Client client) {
		clientService.update(id, client);
		return "redirect:/update";
	}

}
