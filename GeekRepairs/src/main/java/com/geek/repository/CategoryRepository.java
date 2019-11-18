package com.geek.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.geek.model.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>{


    @Query(value = "SELECT MAX(id) FROM Category")
    Long findTopByOrderByIdDesc();

    /**
     * @param category  title of an article
     * @return          List of articles with the same category
     */
    
    
    
	Page<Category> findByName(Pageable pageable,String name);
	
	  //category name must be unique
    @Query("SELECT a FROM Category a WHERE a.name=:name")
    List<Category> findByCategoryName(@Param("name") String category);

	

    
}
