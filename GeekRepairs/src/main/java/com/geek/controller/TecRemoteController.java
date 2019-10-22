package com.geek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.geek.model.entity.TecRemote;
import com.geek.service.TecRemoteService;



@Controller
@RequestMapping("/tecsremote")
public class TecRemoteController {

	@Autowired
	private TecRemoteService tecRemoteService;
	
	
	
	@GetMapping
	public String ShowAllTecsRemote(Model model) {
		model.addAttribute("tecsremote",tecRemoteService.getAll());
	
		
		return "tecsremote/list";
	}
	
	@GetMapping("/new")
	public String newTecRemote(Model model) {
		model.addAttribute("tecRemote",new TecRemote());
		
		return "tecsremote/new";
	}
	
	@PostMapping("/save")
    public String saveNewTecRemote(TecRemote tec) {
        long id = tecRemoteService.create(tec);
        return "redirect:/tecsremote";
    }

	
	@GetMapping("/edit/{id}")
    public String editTecForm(@PathVariable("id") long id, Model model) {
        TecRemote tecnico = tecRemoteService.getOneById(id);
        
        model.addAttribute("tecRemote", tecnico);
        return "tecsremote/edit";
    }

	
	@PostMapping("/update/{id}")
    public String updateTec(@PathVariable("id") long id, TecRemote tec) {
        tecRemoteService.update(id, tec);
        return "redirect:/tecsremote";    
    }
	
	@GetMapping("/delete/{id}")
    public String deleteTecRemote(@PathVariable("id") long id, Model model) {
        TecRemote tec = tecRemoteService.getOneById(id);
        
        model.addAttribute("tecRemote", tec);
        
        return "tecsremote/delete";
    }
	
	@PostMapping("/drop/{id}")
    public String dropTecRemote(@PathVariable("id") long id) {
		tecRemoteService.delete(id);
        
        return "redirect:/tecsremote";    
    }

	
	
}
