package com.geek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


import com.geek.model.Client;



public interface ClientRepository extends PagingAndSortingRepository<Client, Long>{
	
	@Query(value = "SELECT MAX(id) FROM Client")
    Long findTopByOrderByIdDesc();
	
	@Query("SELECT a FROM Client a WHERE a.name=:name")
    List<Client> findByClientName(@Param("name") String client);

}

