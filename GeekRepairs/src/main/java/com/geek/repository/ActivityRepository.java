package com.geek.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.geek.model.Activity;


public interface ActivityRepository extends PagingAndSortingRepository<Activity,Long>{

	 @Query(value = "SELECT MAX(id) FROM Activity")
	    Long findTopByOrderByIdDesc();

	    /**
	     * @param title     title of an article
	     * @param author    author of an article
	     * @return          List of articles with the same title and author
	     * 
	     */
	    @Query("SELECT a FROM Activity a WHERE a.name=:name")
	    List<Activity> findByActivityIndName(@Param("name") String act);
	    Page<Activity> findAll(Pageable pageable);
		
	
	
}
