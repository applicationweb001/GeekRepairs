package com.geek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


import com.geek.model.Specialty;



public interface SpecialtyRepository extends PagingAndSortingRepository<Specialty, Long>{


	/**
     * @return newest specialtyId
     */
    @Query(value = "SELECT MAX(id) FROM Specialty")
    Long findTopByOrderByIdDesc();

    /**
     * @param specialty  name
     * @return          List of specialties with the same name
     */
    
    //specialty name must be unique
    @Query("SELECT s FROM Specialty s WHERE s.name=:name")
    List<Specialty> findBySpecialtyName(@Param("name") String specialty);

	
	
	
}
