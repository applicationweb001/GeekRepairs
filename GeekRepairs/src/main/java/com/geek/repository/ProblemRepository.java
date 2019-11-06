package com.geek.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.geek.model.Category;
import com.geek.model.Problem;

@Repository
public interface ProblemRepository extends PagingAndSortingRepository<Problem, Long>{


	/**
     * @return newest articleId
     */
    @Query(value = "SELECT MAX(id) FROM Problem")
    Long findTopByOrderByIdDesc();

    /**
     * @param title     title of an article
     * @param author    author of an article
     * @return          List of articles with the same title and author
     */
    //title+author must be unique
   
	/**
     * @param pageable
     * @return          a page of entities that fulfill the restrictions
     *                  specified by the Pageable object
     */
    @Query("SELECT a FROM Problem a WHERE a.name=:name")
    List<Problem> findByProblemName(@Param("name") String name);

    
    Page<Problem> findAll(Pageable pageable);
}
