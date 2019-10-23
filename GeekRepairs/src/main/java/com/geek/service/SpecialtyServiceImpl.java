package com.geek.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.geek.model.entity.Specialty;
import com.geek.model.repository.SpecialtyRepository;
import com.geek.service.SpecialtyService;


@Service
public class SpecialtyServiceImpl implements SpecialtyService{

	@Autowired
	private SpecialtyRepository specialtyRepository;

	@Override
	public List<Specialty> getAll() {
		// TODO Auto-generated method stub
		return specialtyRepository.findAll();
	}

	@Override
	public Specialty getOneById(Long id) {
		// TODO Auto-generated method stub
		return specialtyRepository.findById(id).orElseThrow(() -> new RuntimeException("Specialty Not Found!"));
	}

	@Override
	public Long create(Specialty entity) {
		// TODO Auto-generated method stub
		specialtyRepository.save(entity);
		return entity.getId();
	}

	@Override
	public void update(Long id, Specialty entity) {
		Specialty currentSpecialty = getOneById(id);
		currentSpecialty.setName(entity.getName());
		currentSpecialty.setDescription(entity.getDescription());		
		specialtyRepository.save(currentSpecialty);		
	}


	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		specialtyRepository.deleteById(id);
	}

	
	
}
