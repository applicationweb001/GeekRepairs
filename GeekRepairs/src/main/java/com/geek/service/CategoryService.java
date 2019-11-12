package com.geek.service;

import java.util.List;

import com.geek.model.Category;

public interface CategoryService extends CrudService<Category, Long>{
 
	boolean CategoryValid(Category category);
	
	public List<Category> getAllName(String name);
	 
}
