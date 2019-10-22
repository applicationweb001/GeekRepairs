package com.geek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.geek.model.entity.TechnicianInd;
import com.geek.service.TechnicianIndService;

@Controller
@RequestMapping("/techniciansInd")
public class TechnicianIndController {

	@Autowired
	private TechnicianIndService technicianIndService;
	
	@GetMapping
    public String showAllTechniciansInd(Model model) {
        model.addAttribute("techniciansInd", technicianIndService.getAll());
        return "techniciansInd/list";
    }
	
	
	@GetMapping("/new")
    public String newTechIndForm(Model model) {
        model.addAttribute("technicianInd", new TechnicianInd());
        return "techniciansInd/new";
    }

	
	@PostMapping("/save")
    public String saveNewTechInd(TechnicianInd technicianInd) {
        long id = technicianIndService.create(technicianInd);
        return "redirect:/techniciansInd";
    }
	
	
	@GetMapping("/edit/{id}")
    public String editTechnicianIndForm(@PathVariable("id") long id, Model model) {
        TechnicianInd technicianInd = technicianIndService.getOneById(id);
        System.out.println(technicianInd.getName());
        model.addAttribute("technicianInd", technicianInd);
        return "techniciansInd/edit";
    }
	
	
	@PostMapping("/update/{id}")
    public String updateTechnicianInd(@PathVariable("id") long id, TechnicianInd technicianInd) {
        technicianIndService.update(id, technicianInd);
        return "redirect:/techniciansInd";    
    }
	
}
