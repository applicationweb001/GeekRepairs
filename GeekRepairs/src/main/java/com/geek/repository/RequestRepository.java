package com.geek.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.geek.model.Request;

public interface RequestRepository extends PagingAndSortingRepository<Request, Long>{

	/**
     * @return newest requestId
     */
    @Query(value = "SELECT MAX(id) FROM Request")
    Long findTopByOrderByIdDesc();

    /**
     * @param Id  product
     * @return          List of requests with the same name
     */

    @Query("SELECT r FROM Request r WHERE r.id=:id")
    List<Request> findByRequestId(@Param("id") Long Id);
    Page<Request> findAll(Pageable pageable);
	
	
}