package com.geek.service;

import java.util.List;

public interface CrudService<T, ID> {

	List<T> getAll();

	T getOneById(ID id);

	Long create(T entity);

	void update(ID id, T entity);

	void delete(ID id);
}