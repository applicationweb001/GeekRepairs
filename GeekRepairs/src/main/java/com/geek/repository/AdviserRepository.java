package com.geek.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.geek.model.Adviser;


public interface AdviserRepository extends PagingAndSortingRepository<Adviser,Long>{

	 @Query(value = "SELECT MAX(id) FROM Adviser")
	    Long findTopByOrderByIdDesc();

	    /**
	     * @param title     title of an article
	     * @param author    author of an article
	     * @return          List of articles with the same title and author
	     * 
	     */
	    @Query("SELECT a FROM Adviser a WHERE a.name=:name")
	    List<Adviser> findByTechnicianIndName(@Param("name") String tecRemote);
	    Page<Adviser> findAll(Pageable pageable);
		
	
}
