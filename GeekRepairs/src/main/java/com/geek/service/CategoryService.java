package com.geek.service;

import com.geek.model.Category;

public interface CategoryService extends CrudService<Category, Long>{
 
	boolean CategoryValid(Category category);
	
}
