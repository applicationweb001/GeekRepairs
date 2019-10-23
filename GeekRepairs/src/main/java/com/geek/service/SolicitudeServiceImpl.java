package com.geek.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geek.model.entity.Solicitude;
import com.geek.model.repository.SolicitudeRepository;
import com.geek.service.SolicitudeService;


@Service
public class SolicitudeServiceImpl implements SolicitudeService{

	@Autowired
	private SolicitudeRepository solicitudeRepository;
	
	@Override
	public List<Solicitude> getAll() {
		// TODO Auto-generated method stub
		return solicitudeRepository.findAll();
	}

	@Override
	public Solicitude getOneById(Long id) {
		// TODO Auto-generated method stub
		return solicitudeRepository.findById(id).orElseThrow(() -> new RuntimeException("Solicitude Not Found!"));
	}

	@Override
	public Long create(Solicitude entity) {
		// TODO Auto-generated method stub
		solicitudeRepository.save(entity);
		return entity.getId();
	}

	@Override
	public void update(Long id, Solicitude entity) {
		// TODO Auto-generated method stub
		Solicitude currentSolicitude = getOneById(id);
		currentSolicitude.setStatus(entity.getStatus());	
		solicitudeRepository.save(currentSolicitude);	
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		solicitudeRepository.deleteById(id);
	}

	
}
