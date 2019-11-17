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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.geek.common.PageInitPagination;
import com.geek.model.Product;
import com.geek.model.Request;
import com.geek.model.RequestDetail;
import com.geek.model.Ticket;
import com.geek.service.ProductService;
import com.geek.service.RequestService;
import com.geek.service.TicketService;

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
	private TicketService ticketService;
		
	
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
	@GetMapping("{id}/new")
	public String newRequest(@PathVariable(value = "id") Long ticketId,Model model) {

		// in case of redirection model will contain article
		Request request = new Request();
		Ticket ticket = ticketService.findById(ticketId);
		request.setTicketid(ticket);
		
		if (!model.containsAttribute("request")) {
			model.addAttribute("request", request);
			
						
		}
		return REQUEST_ADD_FORM_VIEW;
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping("{id}/create")
	public String createRequest(@PathVariable(value = "id") Long ticketId,@Valid Request request, BindingResult result
			, Model model, RedirectAttributes attr, @RequestParam(name="item_id[]",required=false) Long[] itemId,
			 @RequestParam(name="quantity[]",required=false) Integer[] quantity,SessionStatus status) {
try {
			
			Ticket ticket = ticketService.findById(ticketId);
			
			if(itemId==null || itemId.length==0) {
				model.addAttribute("info","Solicitud no tiene productos");
				return "requests/newRequest";
			}
			
			for (int i = 0; i < itemId.length; i++) {
				Product product=productService.findById(itemId[i]);
				if(product != null) {
					RequestDetail line=new RequestDetail();
					line.setQuantity(quantity[i]);
					line.setProductid(product);
					request.addRequestDetail(line);
				}
			}
			request.setTicketid(ticket);
			requestService.create(request);
			status.setComplete();
			model.addAttribute("success","Solicitud Generada");
		} catch (Exception e) {
			model.addAttribute("error",e.getMessage());
		}
		
		return "redirect:/requests/"+ request.getId();
	}
	
	
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("{id}/edit")
	public String editRequest(@PathVariable(value = "id") Long requestId, Model model) {
		/*
		 * in case of redirection from '/article/{id}/update' model will contain article
		 * with field values
		 */		
		
		if (!model.containsAttribute("request")) {
			model.addAttribute("request", requestService.findById(requestId));
		}
		
		return REQUEST_EDIT_FORM_VIEW;
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping(path = "/{id}/update")
	public String updateRequest(@PathVariable(value = "id") Long requestId, 
			@Valid Request requestDetails,
			BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() ) {

			/// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.request", result);
			attr.addFlashAttribute("request", requestDetails);


			return "redirect:/requests/" + requestDetails.getId()+ "/edit";
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