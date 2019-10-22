package com.geek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geek.model.entity.Specialty;
import com.geek.service.SpecialtyService;


@Controller
@RequestMapping("/specialties")
public class SpecialtyController {

	@Autowired
    private SpecialtyService specialtyService;

	@GetMapping
    public String showAllSpecialties(Model model) {
        model.addAttribute("specialties", specialtyService.getAll());
        return "specialties/list";
    }
	
	
	@GetMapping("/new")
    public String newCatForm(Model model) {
        model.addAttribute("specialty", new Specialty());
        return "specialties/new";
    }

	
	@PostMapping("/save")
    public String saveNewSpecialty(Specialty specialty) {
        long id = specialtyService.create(specialty);
        return "redirect:/specialties";
    }
	
	
	@GetMapping("/edit/{id}")
    public String editSpecialtyForm(@PathVariable("id") long id, Model model) {
		Specialty specialty = specialtyService.getOneById(id);
        System.out.println(specialty.getName());
        model.addAttribute("specialty", specialty);
        return "specialties/edit";
    }
	
	
	@PostMapping("/update/{id}")
    public String updateSpecialty(@PathVariable("id") long id, Specialty specialty) {
        specialtyService.update(id, specialty);
        return "redirect:/specialties";    
    }
	
	@PostMapping("/show/{id}")
    public String showSpecialty(@PathVariable("id") long id, Specialty specialty) {
        specialtyService.update(id, specialty);
        return "redirect:/specialties";    
    }
	
}
