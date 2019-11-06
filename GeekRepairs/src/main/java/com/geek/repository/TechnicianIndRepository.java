package com.geek.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


import com.geek.model.TechnicianInd;

public interface TechnicianIndRepository extends PagingAndSortingRepository<TechnicianInd,Long>{

	/**
     * @return newest articleId
     */
    @Query(value = "SELECT MAX(id) FROM TechnicianInd")
    Long findTopByOrderByIdDesc();

    /**
     * @param title     title of an article
     * @param author    author of an article
     * @return          List of articles with the same title and author
     * 
     */
    @Query("SELECT a FROM Category a WHERE a.name=:name")
    List<TechnicianInd> findByTechnicianIndName(@Param("name") String technicianInd);
    Page<TechnicianInd> findAll(Pageable pageable);
	
}
