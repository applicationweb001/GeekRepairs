package com.geek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geek.model.entity.Problem;

import com.geek.service.ProblemService;


@Controller
@RequestMapping("/problems")
public class ProblemController {

	@Autowired
	private ProblemService problemService;
	
	@GetMapping
	public String ShowAllProblems(Model model) {
		model.addAttribute("problems",problemService.getAll());
	
		
		return "problems/list";
	}
	
	@GetMapping("/new")
	public String newProblem(Model model) {
		model.addAttribute("problem",new Problem());
		
		return "problems/new";
	}
	
	@PostMapping("/save")
    public String saveNewProblem(Problem prob) {
        long id = problemService.create(prob);
        return "redirect:/problems";
    }

	
	@GetMapping("/edit/{id}")
    public String editProblem(@PathVariable("id") long id, Model model) {
        Problem prob = problemService.getOneById(id);
        
        model.addAttribute("problem", prob);
        return "problems/edit";
    }

	
	@PostMapping("/update/{id}")
    public String updateProblem(@PathVariable("id") long id, Problem prob) {
        problemService.update(id, prob);
        return "redirect:/problems";    
    }

	@GetMapping("/delete/{id}")
    public String deleteProblem(@PathVariable("id") long id, Model model) {
        Problem prob = problemService.getOneById(id);
        
        model.addAttribute("problem", prob);
        
        return "problems/delete";
    }
	
	@PostMapping("/drop/{id}")
    public String dropProblem(@PathVariable("id") long id) {
        problemService.delete(id);
        
        return "redirect:/problems";    
    }
	
	@GetMapping("/search/{id}")
    public String searchProblem(@PathVariable("id") long id, Model model) {
           
		Problem prob = problemService.getOneById(id);
    	model.addAttribute("problem",prob);
        
        return "problems/search";
    }

}
