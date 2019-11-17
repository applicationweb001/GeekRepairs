package com.geek.controller;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.geek.common.PageInitPagination;
import com.geek.model.Category;
import com.geek.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	protected static final String CATEGORY_VIEW = "categories/showCategory"; // view template for single article
	protected static final String CATEGORY_ADD_FORM_VIEW = "categories/newCategory"; // form for new article
	protected static final String CATEGORY_EDIT_FORM_VIEW = "categories/editCategory"; // form for editing an article
	protected static final String CATEGORY_PAGE_VIEW = "categories/allCategories"; // list with pagination
	protected static final String INDEX_VIEW = "index"; // articles with pagination

	@Autowired
	private PageInitPagination pageInitiPagination;

	@Autowired
	private CategoryService categoryService;

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public String getCategoryById(@PathVariable(value = "id") Long categoryId, Model model) {
		model.addAttribute("category", categoryService.findById(categoryId));
		return CATEGORY_VIEW;
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping
	public ModelAndView getAllCategories(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = pageInitiPagination.initPaginationCategory(pageSize, page, CATEGORY_PAGE_VIEW,1,"");

		return modelAndView;

	}

	@GetMapping("/search")
	public ModelAndView getAllCategoriesByAlgo(@RequestParam("name") String algo,@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {

		
		ModelAndView modelAndView = pageInitiPagination.initPaginationCategory(pageSize, page, CATEGORY_PAGE_VIEW,2,algo);

		return modelAndView;

	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/new")
	public String newCategory(Model model) {

		// in case of redirection model will contain article
		if (!model.containsAttribute("category")) {
			model.addAttribute("category", new Category());
			String numero = (String) model.getAttribute("buscarAlgo");
		}
		return CATEGORY_ADD_FORM_VIEW;
	}

	@Secured({ "ROLE_ADMIN" })
	@PostMapping("/create")
	public String createCategory(@Valid Category category, BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() || categoryService.CategoryValid(category) == false) {

			// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.article", result);
			attr.addFlashAttribute("category", category);

			attr.addFlashAttribute("error", "No se permite categorias" + " con el mismo nombre");

			return "redirect:/categories/new";
		}
		Category newCategory = categoryService.create(category);
		model.addAttribute("category", newCategory);

		return "redirect:/categories/" + newCategory.getId();
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping("{id}/edit")
	public String editCategory(@PathVariable(value = "id") Long categoryId, Model model) {
		/*
		 * in case of redirection from '/article/{id}/update' model will contain article
		 * with field values
		 */
		if (!model.containsAttribute("category")) {
			model.addAttribute("category", categoryService.findById(categoryId));
		}
		return CATEGORY_EDIT_FORM_VIEW;
	}

	@Secured({ "ROLE_ADMIN" })
	@PostMapping(path = "/{id}/update")
	public String updateCategory(@PathVariable(value = "id") Long categoryId, @Valid Category categoryDetails,
			BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() || categoryService.CategoryValid(categoryDetails) == false) {

			/// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.category", result);
			attr.addFlashAttribute("category", categoryDetails);

			attr.addFlashAttribute("error", "No se permite articulos con el mismo titulo y autor");

			return "redirect:/categories/" + categoryDetails.getId() + "/edit";
		}

		categoryService.update(categoryId, categoryDetails);
		model.addAttribute("category", categoryService.findById(categoryId));
		return "redirect:/categories/" + categoryId;
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping(value = "/{id}/delete")
	public String deleteCategory(@PathVariable("id") Long categoryId) {
		// Article article = articleService.findById(categoryId);
		// String title = article.getTitle();
		categoryService.delete(categoryId);
		return "redirect:/categories";
	}

}
