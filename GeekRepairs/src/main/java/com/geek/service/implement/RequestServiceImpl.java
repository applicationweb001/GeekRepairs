package com.geek.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.geek.exception.ResourceNotFoundException;
import com.geek.model.Request;
import com.geek.repository.RequestRepository;
import com.geek.service.RequestService;

@Service
public class RequestServiceImpl implements RequestService{
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Override
	public List<Request> getAll() {
		List<Request> requests = new ArrayList<>();
		requestRepository.findAll().iterator().forEachRemaining(requests::add);
		return requests;
	}

	@Override
	public Request create(Request object) {
		Request newrequest;
		newrequest = requestRepository.save(object);
		return newrequest;
	}

	@Override
	public Request update(Long id, Request objectupdate) {
		Request request = findById(id);
		request.setPrice(objectupdate.getPrice());
		request.setQuantity(objectupdate.getQuantity());
		request.setProduct(objectupdate.getProduct());
		
		requestRepository.save(request);	
		return request;
	}

	@Override
	public void delete(Long id) {
		requestRepository.delete(findById(id));
	}

	@Override
	public Request findById(Long id) {
		Optional<Request> request = requestRepository.findById(id);

		if (!request.isPresent()) {
            throw new ResourceNotFoundException("There is no Request with ID = " + id);
        }

		return request.get();
	}

	@Override
	public Request getLatestEntry() {
		 List<Request> request = getAll();
	        if(!request.isEmpty()){
	            return null;
	        }
	        else{
	            Long latestRequestID = requestRepository.findTopByOrderByIdDesc();
	            return findById(latestRequestID);
	        }
	}

	@Override
	public Page<Request> findAll(Pageable pageable) {
		 return requestRepository.findAll(pageable);
	}

	@Override
	public boolean RequestValid(Request request) {
		List<Request> requests= new ArrayList<>();
		requestRepository.findByRequestProduct(request.getProduct())
                .iterator().forEachRemaining(requests::add);
        if (!requests.isEmpty()) { return false;}
        else {return true;}
	}


	

}
