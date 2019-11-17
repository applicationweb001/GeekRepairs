package com.geek.service;

import java.util.List;

import com.geek.model.Product;

public interface ProductService extends CrudService<Product,Long>{

	boolean ProductValid(Product product); 
		
	List<Product> findByName(String name);


    List<Product> fetchProductByName(String name); 
	
}


