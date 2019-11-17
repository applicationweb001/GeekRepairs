package com.geek.service;


import java.util.Optional;


import com.geek.model.Request;

public interface RequestService extends CrudService<Request, Long>{

	boolean RequestValid(Request request);
	
	Optional<Request> fetchByRequestIdWithRequestDetailWithProduct(Long id);
    
		
	

}
