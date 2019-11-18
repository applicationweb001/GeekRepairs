package com.geek.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.geek.model.Satisfaction;

public interface SatisfactionRepository extends PagingAndSortingRepository<Satisfaction,Long>{
	@Query(value = "SELECT MAX(id) FROM Satisfaction")
    Long findTopByOrderByIdDesc();
	
	@Query(value="SELECT s FROM Satisfaction s WHERE s.ticket.id=:id")
	Satisfaction findByTicket(Long id);
}

