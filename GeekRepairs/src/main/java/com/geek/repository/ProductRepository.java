package com.geek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.geek.model.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product,Long>{
	
	/**
     * @return newest categoryId
     */
    @Query(value = "SELECT MAX(id) FROM Product")
    Long findTopByOrderByIdDesc();

    /**
     * @param category  title of an article
     * @return          List of articles with the same category
     */
    
    //category name must be unique
    @Query("SELECT a FROM Product a WHERE a.name=:name")
    List<Product> findByName(String name);
    
    @Query("SELECT p from Product p WHERE p.name LIKE %?1%")
    List<Product> fetchProductByName(String name); 
    
    
    
}
