package com.geek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geek.model.entity.Problem;
import com.geek.model.entity.Solicitude;
import com.geek.service.SolicitudeService;

@Controller
@RequestMapping("/solicitudes")
public class SolicitudeController {

	@Autowired
    private SolicitudeService solicitudeService;
	

	@GetMapping
    public String showAllSpecialties(Model model) {
        model.addAttribute("solicitudes", solicitudeService.getAll());
        return "solicitudes/list";
    }
	
	
	@GetMapping("/new")
    public String newCatForm(Model model) {
        model.addAttribute("solicitude", new Solicitude());
        return "solicitudes/new";
    }

	
	@PostMapping("/save")
    public String saveNewSpecialty(Solicitude solicitude) {
        long id = solicitudeService.create(solicitude);
        return "redirect:/solicitudes";
    }
	
	
	@GetMapping("/edit/{id}")
    public String editSolicitudeForm(@PathVariable("id") long id, Model model) {
		Solicitude solicitude = solicitudeService.getOneById(id);
        System.out.println(solicitude.getStatus());
        model.addAttribute("solicitude", solicitude);
        return "solicitudes/edit";
    }
	
	
	@PostMapping("/update/{id}")
    public String updateSolicitude(@PathVariable("id") long id, Solicitude solicitude) {
		solicitudeService.update(id, solicitude);
        return "redirect:/solicitudes";    
    }
	


	@GetMapping("/delete/{id}")
    public String deleteProblem(@PathVariable("id") long id, Model model) {
        Solicitude solicitude = solicitudeService.getOneById(id);
        
        model.addAttribute("solicitude", solicitude);
        
        return "solicitudes/delete";
    }
	
	@PostMapping("/drop/{id}")
    public String dropSolicitude(@PathVariable("id") long id) {
		solicitudeService.delete(id);
        
        return "redirect:/solicitudes";    
    }
	
	@GetMapping("/search/{id}")
    public String searchProblem(@PathVariable("id") long id, Model model) {
           
		Solicitude solicitude = solicitudeService.getOneById(id);
    	model.addAttribute("solicitude",solicitude);
        
        return "solicitudes/search";
    }
	
}
