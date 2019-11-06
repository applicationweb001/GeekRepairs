package com.geek.repository;

import java.util.List;

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
     * @param request  product
     * @return          List of requests with the same name
     */

    @Query("SELECT r FROM Request r WHERE r.product=:product")
    List<Request> findByRequestProduct(@Param("product") String request);
	
	
}