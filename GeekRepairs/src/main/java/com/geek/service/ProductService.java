package com.geek.service;

import com.geek.model.Product;

public interface ProductService extends CrudService<Product,Long>{

	boolean ProductValid(Product product); 

}
