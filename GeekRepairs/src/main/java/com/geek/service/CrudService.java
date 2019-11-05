package com.geek.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<T, ID> {

	List<T> getAll();

	T create(T object);

	T update(ID id, T objectupdate);

	void delete(ID objectId);

	T findById(ID id);
	
	/**
     * @return newest object
     */
    T getLatestEntry();


	//Pagination
    Page<T> findAll(Pageable pageable);
}