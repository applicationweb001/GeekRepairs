package com.geek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geek.model.entity.Category;
import com.geek.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
    private CategoryService categoryService;

	@GetMapping
    public String showAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "categories/list";
    }
	
	
	@GetMapping("/new")
    public String newCatForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/new";
    }

	
	@PostMapping("/save")
    public String saveNewCategory(Category category) {
        long id = categoryService.create(category);
        return "redirect:/categories";
    }
	
	
	@GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable("id") long id, Model model) {
        Category category = categoryService.getOneById(id);
        System.out.println(category.getName());
        model.addAttribute("category", category);
        return "categories/edit";
    }
	
	
	@PostMapping("/update/{id}")
    public String updateCategory(@PathVariable("id") long id, Category category) {
        categoryService.update(id, category);
        return "redirect:/categories";    
    }
}
