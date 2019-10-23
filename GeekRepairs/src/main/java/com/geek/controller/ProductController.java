package com.geek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geek.model.entity.Category;
import com.geek.model.entity.Product;
import com.geek.model.entity.TechnicianInd;
import com.geek.service.CategoryService;
import com.geek.service.ProductService;

@Controller 
@RequestMapping("/products")

public class ProductController {
//sada
	@Autowired
    private ProductService productService;
	@Autowired
    private CategoryService categoryService;

	@GetMapping
    public String showAllProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        model.addAttribute("categories", categoryService.getAll());
        return "products/list";
    }
	
	
	@GetMapping("/new")
    public String newProForm(Model model) {
        model.addAttribute("product", new Product());
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "products/new";
    }

	
	@PostMapping("/save")
    public String saveNewProduct(Product product) {
        long id = productService.create(product);
        return "redirect:/products";
    }
	
	
	@GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable("id") long id, Model model) {
        Product product = productService.getOneById(id);
        System.out.println(product.getName());
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        return "products/edit";
    }
	
	
	@PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") long id, Product product) {
        productService.update(id, product);
        return "redirect:/products";    
    }

	@GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id, Model model) {
		Product pro = productService.getOneById(id);
        
        model.addAttribute("product", pro);
        
        return "products/delete";
    }
	
	@PostMapping("/drop/{id}")
    public String dropPro(@PathVariable("id") long id) {
		productService.delete(id);
        
        return "redirect:/products";    
    }
	
}
