package com.geek.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.geek.exception.ResourceNotFoundException;
import com.geek.model.Specialty;
import com.geek.repository.SpecialtyRepository;
import com.geek.service.SpecialtyService;

@Service
public class SpecialtyServiceImpl implements SpecialtyService{
	
	@Autowired
	private SpecialtyRepository specialtyRepository;
	
	@Override
	public List<Specialty> getAll() {
		List<Specialty> specialties = new ArrayList<>();
		specialtyRepository.findAll().iterator().forEachRemaining(specialties::add);
		return specialties;
	}

	@Override
	public Specialty create(Specialty object) {
		Specialty newspecialty;
		newspecialty = specialtyRepository.save(object);
		return newspecialty;
	}

	@Override
	public Specialty update(Long id, Specialty objectupdate) {
		Specialty specialty = findById(id);
		
		specialty.setName(objectupdate.getName());
		
		specialtyRepository.save(specialty);	
		return specialty;
	}

	@Override
	public void delete(Long id) {
		specialtyRepository.delete(findById(id));
	}

	@Override
	public Specialty findById(Long id) {
		Optional<Specialty> specialty = specialtyRepository.findById(id);

		if (!specialty.isPresent()) {
            throw new ResourceNotFoundException("There is no Specialty with ID = " + id);
        }

		return specialty.get();
	}

	@Override
	public Specialty getLatestEntry() {
		 List<Specialty> specialty = getAll();
	        if(specialty.isEmpty()){
	            return null;
	        }
	        else{
	            Long latestSpecialtyID = specialtyRepository.findTopByOrderByIdDesc();
	            return findById(latestSpecialtyID);
	        }
	}

	@Override
	public Page<Specialty> findAll(Pageable pageable) {
		 return specialtyRepository.findAll(pageable);
	}

	@Override
	public boolean SpecialtyValid(Specialty specialty) {
		List<Specialty> specialties= new ArrayList<>();
		specialtyRepository.findBySpecialtyName(specialty.getName())
                .iterator().forEachRemaining(specialties::add);
        if (!specialties.isEmpty()) { return false;}
        else {return true;}
		
	}

}
