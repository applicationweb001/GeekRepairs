package com.geek.common;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.geek.model.Article;
import com.geek.model.Category;
import com.geek.model.Specialty;
import com.geek.service.ArticleService;
import com.geek.service.CategoryService;
import com.geek.service.SpecialtyService;

@Component
public class PageInitPagination {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SpecialtyService specialtyService;
	

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
	
}
