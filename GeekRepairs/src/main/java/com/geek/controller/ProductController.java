package com.geek.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.geek.common.PageInitPagination;
import com.geek.model.Category;
import com.geek.model.Client;
import com.geek.model.Product;
import com.geek.model.Ticket;
import com.geek.service.CategoryService;
import com.geek.service.ProductService;

@Controller
@RequestMapping("/products")
@SessionAttributes("product")
public class ProductController {
	protected static final String PRODUCT_VIEW = "products/showProduct"; // view template for single article
	protected static final String PRODUCT_ADD_FORM_VIEW = "products/newProduct"; // form for new article
	protected static final String PRODUCT_EDIT_FORM_VIEW = "products/editProduct"; // form for editing an article
	protected static final String PRODUCT_PAGE_VIEW = "products/allProducts"; // list with pagination
	protected static final String INDEX_VIEW = "index"; // articles with pagination
	
	@Autowired
	private PageInitPagination pageInitiPagination;
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public String getProductById(@PathVariable(value = "id") Long productId, Model model) {
		model.addAttribute("product", productService.findById(productId));
		return PRODUCT_VIEW;
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping
	public ModelAndView getAllProducts(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = pageInitiPagination.initPaginationProduct(pageSize, page, PRODUCT_PAGE_VIEW);
		return modelAndView;

	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/new")
	public String newProduct(Model model) {
		List<Category> categories = categoryService.getAll(); //se agrego esto
		model.addAttribute("categories",categories); // y esto
		// in case of redirection model will contain article
		if (!model.containsAttribute("product")) {
			model.addAttribute("product", new Product());
			
		}
		return PRODUCT_ADD_FORM_VIEW;
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping("/create")
	public String createProduct(@Valid Product product, BindingResult result
			, Model model, RedirectAttributes attr) {

		if (result.hasErrors() ) {

			// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.product", result);
			attr.addFlashAttribute("product", product);
			List<Category> categories = categoryService.getAll(); //se agrego esto
			model.addAttribute("categories",categories); // y esto
			

			return "redirect:/products/new";
		}
		Product newProduct= productService.create(product);
		model.addAttribute("product", newProduct);

		return "redirect:/products/" + newProduct.getId();
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("{id}/edit")
	public String editProduct(@PathVariable(value = "id") Long productId, Model model) {
		/*
		 * in case of redirection from '/article/{id}/update' model will contain article
		 * with field values
		 */
		List<Category> categories = categoryService.getAll(); //se agrego esto
		model.addAttribute("categories",categories); // y esto
		if (!model.containsAttribute("product")) {
			model.addAttribute("product", productService.findById(productId));
			
		}
		return PRODUCT_EDIT_FORM_VIEW;
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping(path = "/{id}/update")
	public String updateProduct(@PathVariable(value = "id") Long productId, 
			@Valid Product productDetails,
			BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() ) {

			/// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.product", result);
			attr.addFlashAttribute("product", productDetails);
			List<Category> categories = categoryService.getAll(); //se agrego esto
			model.addAttribute("categories",categories); // y esto
			

			return "redirect:/products/" + productDetails.getId() + "/edit";
		}
		
		productService.update(productId, productDetails);
		model.addAttribute("product", productService.findById(productId));
		return "redirect:/products/" + productId;
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping(value = "/{id}/delete")
	public String deleteProduct(@PathVariable("id") Long productId) {
		//Article article = articleService.findById(categoryId);
		// String title = article.getTitle();
		productService.delete(productId);
		return "redirect:/products";
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping(value = "/list/{name}",produces = { "application/json" })
	public @ResponseBody List<Product> fetchProducts(@PathVariable String name,Model model){
		List<Product> products = null;
		try {
			products = productService.fetchProductByName(name);
			} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return products;
			
			
		}	
			
	
}