package com.geek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


import com.geek.model.Ticket;

public interface TicketRepository extends PagingAndSortingRepository<Ticket,Long>{
	/**
     * @return newest categoryId
     */
    @Query(value = "SELECT MAX(id) FROM Ticket")
    Long findTopByOrderByIdDesc();

    /**
     * @param category  title of an article
     * @return          List of articles with the same category
     */
    
    //category name must be unique
    @Query("SELECT a FROM Ticket a WHERE a.id=:id")
    List<Ticket> findByTicketId(@Param("id") Long ticket);
}
