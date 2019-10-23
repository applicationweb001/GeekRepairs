package com.geek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geek.model.entity.Problem;
import com.geek.model.entity.Specialty;
import com.geek.model.entity.TechnicianInd;
import com.geek.service.SpecialtyService;
import com.geek.service.TechnicianIndService;

@Controller
@RequestMapping("/techniciansInd")
public class TechnicianIndController {

	@Autowired
	private TechnicianIndService technicianIndService;
	
	@Autowired
	private SpecialtyService specialtyService;
	
	@GetMapping
    public String showAllTechniciansInd(Model model) {
        model.addAttribute("techniciansInd", technicianIndService.getAll());
        model.addAttribute("specialties", specialtyService.getAll());
        return "techniciansInd/list";
    }
	
	
	@GetMapping("/new")
    public String newTechIndForm(Model model) {
        model.addAttribute("technicianInd", new TechnicianInd());
        List<Specialty> specialties = specialtyService.getAll();
        model.addAttribute("specialties", specialties);
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
        List<Specialty> specialties = specialtyService.getAll();
        model.addAttribute("specialties", specialties);
        model.addAttribute("technicianInd", technicianInd);
        return "techniciansInd/edit";
    }
	
	
	@PostMapping("/update/{id}")
    public String updateTechnicianInd(@PathVariable("id") long id, TechnicianInd technicianInd) {
        technicianIndService.update(id, technicianInd);
        return "redirect:/techniciansInd";    
    }
	
	@GetMapping("/delete/{id}")
    public String deleteProblem(@PathVariable("id") long id, Model model) {
        TechnicianInd tech = technicianIndService.getOneById(id);
        
        model.addAttribute("technicianInd", tech);
        
        return "techniciansInd/delete";
    }
	
	@PostMapping("/drop/{id}")
    public String dropTech(@PathVariable("id") long id) {
		technicianIndService.delete(id);
        
        return "redirect:/techniciansInd";    
    }
	
	
	
}
