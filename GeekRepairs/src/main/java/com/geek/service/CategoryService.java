package com.geek.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.geek.model.Category;

public interface CategoryService extends CrudService<Category, Long>{
 
	boolean CategoryValid(Category category);
	
	public Page<Category> findByName(Pageable pageable,String name);
	 
}
