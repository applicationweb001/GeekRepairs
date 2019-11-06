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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.geek.common.PageInitPagination;
import com.geek.model.Product;
import com.geek.model.Request;
import com.geek.service.ProductService;
import com.geek.service.RequestService;

@Controller
@RequestMapping("/requests")
public class RequestController {

	protected static final String REQUEST_VIEW = "requests/showRequest"; // view template for single article
	protected static final String REQUEST_ADD_FORM_VIEW = "requests/newRequest"; // form for new article
	protected static final String REQUEST_EDIT_FORM_VIEW = "requests/editRequest"; // form for editing an article
	protected static final String REQUEST_PAGE_VIEW = "requests/allRequests"; // list with pagination
	protected static final String INDEX_VIEW = "index"; // articles with pagination

	@Autowired
	private PageInitPagination pageInitiPagination;
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private ProductService productService;
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public String getRequestById(@PathVariable(value = "id") Long requestId, Model model) {
		model.addAttribute("request", requestService.findById(requestId));
		return REQUEST_VIEW;
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping
	public ModelAndView getAllRequests(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = pageInitiPagination.initPaginationRequest(pageSize, page, REQUEST_PAGE_VIEW);
		return modelAndView;

	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/new")
	public String newRequest(Model model) {

		// in case of redirection model will contain article
		if (!model.containsAttribute("request")) {
			model.addAttribute("request", new Request());
			List<Product> products = productService.getAll();
			model.addAttribute("products",products);
		}
		return REQUEST_ADD_FORM_VIEW;
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping("/create")
	public String createRequest(@Valid Request request, BindingResult result
			, Model model, RedirectAttributes attr) {

		if (result.hasErrors() || requestService.RequestValid(request) == false) {

			// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.request", result);
			attr.addFlashAttribute("request", request);
			List<Product> products = productService.getAll(); 
			attr.addFlashAttribute("product",products);

			attr.addFlashAttribute("error", "No se permite solicitudes"
					+ " con el mismo nombre");

			return "redirect:/requests/new";
		}
		
		Request newRequest= requestService.create(request);
		model.addAttribute("request", newRequest);

		return "redirect:/requests/" + newRequest.getId();
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("{id}/edit")
	public String editRequest(@PathVariable(value = "id") Long requestId, Model model) {
		/*
		 * in case of redirection from '/article/{id}/update' model will contain article
		 * with field values
		 */
		if (!model.containsAttribute("request")) {
			List<Product> products = productService.getAll(); 
			model.addAttribute("products",products); 
			model.addAttribute("request", requestService.findById(requestId));
		}
		return REQUEST_EDIT_FORM_VIEW;
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping(path = "/{id}/update")
	public String updateRequest(@PathVariable(value = "id") Long requestId, 
			@Valid Request requestDetails,
			BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() || requestService.RequestValid(requestDetails) == false) {

			/// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.request", result);
			attr.addFlashAttribute("request", requestDetails);
			List<Product> products = productService.getAll();
			attr.addFlashAttribute("products",products);


			return "redirect:/requests/" + requestDetails.getId() + "/edit";
		}
		
		requestService.update(requestId, requestDetails);
		model.addAttribute("request", requestService.findById(requestId));
		return "redirect:/requests/" + requestId;
	}

	@Secured({"ROLE_ADMIN"})
	@GetMapping(value = "/{id}/delete")
	public String deleteRequest(@PathVariable("id") Long requestId) {
		//Article article = articleService.findById(categoryId);
		// String title = article.getTitle();
		requestService.delete(requestId);
		return "redirect:/requests";
	}
}