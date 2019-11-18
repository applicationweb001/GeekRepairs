package com.geek.common;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.geek.model.Activity;
import com.geek.model.Adviser;
import com.geek.model.Article;
import com.geek.model.Category;
import com.geek.model.Client;
import com.geek.model.Problem;
import com.geek.model.Product;
import com.geek.model.Request;
import com.geek.model.Satisfaction;
import com.geek.model.Specialty;
import com.geek.model.TechnicianInd;
import com.geek.model.Ticket;
import com.geek.service.ActivityService;
import com.geek.service.AdviserService;
import com.geek.service.ArticleService;
import com.geek.service.CategoryService;
import com.geek.service.ClientService;
import com.geek.service.ProblemService;
import com.geek.service.ProductService;
import com.geek.service.RequestService;
import com.geek.service.SatisfactionService;
import com.geek.service.SpecialtyService;
import com.geek.service.TechnicianIndService;
import com.geek.service.TicketService;

@Component
public class PageInitPagination {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SpecialtyService specialtyService;
	
	@Autowired
	private TechnicianIndService technicianIndService;
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private TicketService ticketService;
	
	
	@Autowired
	private AdviserService tecService;
	
	@Autowired
	private ProblemService probService;
	
	@Autowired
	private ActivityService actService;
	
	@Autowired
	private SatisfactionService satisfactionService;

	// pagination
	private static final int BUTTONS_TO_SHOW = 3;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10 };

	public  ModelAndView initPagination(Optional<Integer> pageSize, Optional<Integer> page, String url) {
		ModelAndView initModelView = new ModelAndView(url);
		// If pageSize == null, return initial page size
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		/*
		 * If page == null || page < 0 (to prevent exception), return initial size Else,
		 * return value of param. decreased by 1
		 */
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Article> articlesList = articleService.findAll(PageRequest.of(evalPage, evalPageSize));
		PagerModel pager = new PagerModel(articlesList.getTotalPages(), articlesList.getNumber(), BUTTONS_TO_SHOW);

		initModelView.addObject("articlesList", articlesList);
		initModelView.addObject("selectedPageSize", evalPageSize);
		initModelView.addObject("pageSizes", PAGE_SIZES);
		initModelView.addObject("pager", pager);
		return initModelView;
	}
	
	public  ModelAndView initPaginationCategory(Optional<Integer> pageSize, Optional<Integer> page, String url) {
		ModelAndView initModelView = new ModelAndView(url);
		// If pageSize == null, return initial page size
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		
		/*
		 * If page == null || page < 0 (to prevent exception), return initial size Else,
		 * return value of param. decreased by 1
		 */
		
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Category> categoriesList = categoryService.findAll(PageRequest.of(evalPage, evalPageSize));
		PagerModel pager = new PagerModel(categoriesList.getTotalPages(), categoriesList.getNumber(), BUTTONS_TO_SHOW);

		initModelView.addObject("categoriesList", categoriesList);
		initModelView.addObject("selectedPageSize", evalPageSize);
		initModelView.addObject("pageSizes", PAGE_SIZES);
		initModelView.addObject("pager", pager);

		return initModelView;
	}
	
	public  ModelAndView initPaginationSpecialty(Optional<Integer> pageSize, Optional<Integer> page, String url) {
		ModelAndView initModelView = new ModelAndView(url);
		// If pageSize == null, return initial page size
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		
		/*
		 * If page == null || page < 0 (to prevent exception), return initial size Else,
		 * return value of param. decreased by 1
		 */
		
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Specialty> specialtiesList = specialtyService.findAll(PageRequest.of(evalPage, evalPageSize));
		PagerModel pager = new PagerModel(specialtiesList.getTotalPages(), specialtiesList.getNumber(), BUTTONS_TO_SHOW);

		initModelView.addObject("specialtiesList", specialtiesList);
		initModelView.addObject("selectedPageSize", evalPageSize);
		initModelView.addObject("pageSizes", PAGE_SIZES);
		initModelView.addObject("pager", pager);

		return initModelView;
	}
	public  ModelAndView initPaginationTechnicianInd(Optional<Integer> pageSize, Optional<Integer> page, String url) {
		ModelAndView initModelView = new ModelAndView(url);
		// If pageSize == null, return initial page size
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		
		/*
		 * If page == null || page < 0 (to prevent exception), return initial size Else,
		 * return value of param. decreased by 1
		 */
		
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<TechnicianInd> techniciansIndList = technicianIndService.findAll(PageRequest.of(evalPage, evalPageSize));
		PagerModel pager = new PagerModel(techniciansIndList.getTotalPages(), techniciansIndList.getNumber(), BUTTONS_TO_SHOW);

		initModelView.addObject("techniciansIndList", techniciansIndList);
		initModelView.addObject("selectedPageSize", evalPageSize);
		initModelView.addObject("pageSizes", PAGE_SIZES);
		initModelView.addObject("pager", pager);

		return initModelView;
	}
	
	public  ModelAndView initPaginationRequest(Optional<Integer> pageSize, Optional<Integer> page, String url) {
		ModelAndView initModelView = new ModelAndView(url);
		// If pageSize == null, return initial page size
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		
		/*
		 * If page == null || page < 0 (to prevent exception), return initial size Else,
		 * return value of param. decreased by 1
		 */
		
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Request> requestsList = requestService.findAll(PageRequest.of(evalPage, evalPageSize));
		PagerModel pager = new PagerModel(requestsList.getTotalPages(), requestsList.getNumber(), BUTTONS_TO_SHOW);

		initModelView.addObject("requestsList", requestsList);
		initModelView.addObject("selectedPageSize", evalPageSize);
		initModelView.addObject("pageSizes", PAGE_SIZES);
		initModelView.addObject("pager", pager);

		return initModelView;
	}
	
	
	public  ModelAndView initPaginationProduct(Optional<Integer> pageSize, Optional<Integer> page, String url) {
		ModelAndView initModelView = new ModelAndView(url);
		// If pageSize == null, return initial page size
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		
		/*
		 * If page == null || page < 0 (to prevent exception), return initial size Else,
		 * return value of param. decreased by 1
		 */
		
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Product> productsList = productService.findAll(PageRequest.of(evalPage, evalPageSize));
		PagerModel pager = new PagerModel(productsList.getTotalPages(), productsList.getNumber(), BUTTONS_TO_SHOW);

		initModelView.addObject("productsList", productsList);
		initModelView.addObject("selectedPageSize", evalPageSize);
		initModelView.addObject("pageSizes", PAGE_SIZES);
		initModelView.addObject("pager", pager);

		return initModelView;
	}
	
	public  ModelAndView initPaginationClient(Optional<Integer> pageSize, Optional<Integer> page, String url) {
		ModelAndView initModelView = new ModelAndView(url);
		// If pageSize == null, return initial page size
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		
		/*
		 * If page == null || page < 0 (to prevent exception), return initial size Else,
		 * return value of param. decreased by 1
		 */
		
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Client> clientsList = clientService.findAll(PageRequest.of(evalPage, evalPageSize));
		PagerModel pager = new PagerModel(clientsList.getTotalPages(), clientsList.getNumber(), BUTTONS_TO_SHOW);

		initModelView.addObject("clientsList", clientsList);
		initModelView.addObject("selectedPageSize", evalPageSize);
		initModelView.addObject("pageSizes", PAGE_SIZES);
		initModelView.addObject("pager", pager);

		return initModelView;
	}
	
	public  ModelAndView initPaginationTicket(Optional<Integer> pageSize, Optional<Integer> page, String url) {
		ModelAndView initModelView = new ModelAndView(url);
		// If pageSize == null, return initial page size
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		
		/*
		 * If page == null || page < 0 (to prevent exception), return initial size Else,
		 * return value of param. decreased by 1
		 */
		
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Ticket> ticketsList = ticketService.findAll(PageRequest.of(evalPage, evalPageSize));
		PagerModel pager = new PagerModel(ticketsList.getTotalPages(), ticketsList.getNumber(), BUTTONS_TO_SHOW);

		initModelView.addObject("ticketsList", ticketsList);
		initModelView.addObject("selectedPageSize", evalPageSize);
		initModelView.addObject("pageSizes", PAGE_SIZES);
		initModelView.addObject("pager", pager);

		return initModelView;
	}
	
	
	
	public  ModelAndView initPaginationAdviser(Optional<Integer> pageSize, Optional<Integer> page, String url) {
		ModelAndView initModelView = new ModelAndView(url);
	
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		
	
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Adviser> tecList = tecService.findAll(PageRequest.of(evalPage, evalPageSize));
		PagerModel pager = new PagerModel(tecList.getTotalPages(), tecList.getNumber(), BUTTONS_TO_SHOW);

		initModelView.addObject("tecList", tecList);
		initModelView.addObject("selectedPageSize", evalPageSize);
		initModelView.addObject("pageSizes", PAGE_SIZES);
		initModelView.addObject("pager", pager);

		return initModelView;
	}
	
	
	public  ModelAndView initPaginationProblem(Optional<Integer> pageSize, Optional<Integer> page, String url) {
		ModelAndView initModelView = new ModelAndView(url);
	
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		
	
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Problem> probList = probService.findAll(PageRequest.of(evalPage, evalPageSize));
		PagerModel pager = new PagerModel(probList.getTotalPages(), probList.getNumber(), BUTTONS_TO_SHOW);

		initModelView.addObject("probList", probList);
		initModelView.addObject("selectedPageSize", evalPageSize);
		initModelView.addObject("pageSizes", PAGE_SIZES);
		initModelView.addObject("pager", pager);

		return initModelView;
	}
	
	public  ModelAndView initPaginationActivity(Optional<Integer> pageSize, Optional<Integer> page, String url) {
		ModelAndView initModelView = new ModelAndView(url);
	
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		
	
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Activity> actList = actService.findAll(PageRequest.of(evalPage, evalPageSize));
		PagerModel pager = new PagerModel(actList.getTotalPages(), actList.getNumber(), BUTTONS_TO_SHOW);

		initModelView.addObject("actList", actList);
		initModelView.addObject("selectedPageSize", evalPageSize);
		initModelView.addObject("pageSizes", PAGE_SIZES);
		initModelView.addObject("pager", pager);

		return initModelView;
	}
	public  ModelAndView initPaginationSatisfaction(Optional<Integer> pageSize, Optional<Integer> page, String url) {
		ModelAndView initModelView = new ModelAndView(url);
	
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		
	
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Satisfaction> satisfactionList = satisfactionService.findAll(PageRequest.of(evalPage, evalPageSize));
		PagerModel pager = new PagerModel(satisfactionList.getTotalPages(), satisfactionList.getNumber(), BUTTONS_TO_SHOW);

		initModelView.addObject("satisfactionList", satisfactionList);
		initModelView.addObject("selectedPageSize", evalPageSize);
		initModelView.addObject("pageSizes", PAGE_SIZES);
		initModelView.addObject("pager", pager);

		return initModelView;
	}
}
