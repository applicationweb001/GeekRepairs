package com.geek.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.geek.model.Specialty;
import com.geek.model.TechnicianInd;

public interface TechnicianIndService extends CrudService<TechnicianInd, Long> {
	boolean TechnicianIndValid(TechnicianInd technicianInd);
	
	List<TechnicianInd> findBySpecialtyName(String name);
}
