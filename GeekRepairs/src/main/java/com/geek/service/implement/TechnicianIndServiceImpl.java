package com.geek.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.geek.exception.ResourceNotFoundException;
import com.geek.model.TechnicianInd;
import com.geek.repository.TechnicianIndRepository;
import com.geek.service.TechnicianIndService;
@Service

public class TechnicianIndServiceImpl implements TechnicianIndService {

	@Autowired
	private TechnicianIndRepository technicianIndRepository;
	
	@Override
	public List<TechnicianInd> getAll() {
		List<TechnicianInd> techniciansInd = new ArrayList<>();
		technicianIndRepository.findAll().iterator().forEachRemaining(techniciansInd::add);
		return techniciansInd;
	}

	@Override
	public TechnicianInd create(TechnicianInd object) {
		TechnicianInd newtechnicianInd;
		newtechnicianInd = technicianIndRepository.save(object);
		return newtechnicianInd;
	}

	@Override
	public TechnicianInd update(Long id, TechnicianInd objectupdate) {
		TechnicianInd technicianInd = findById(id);
		
		technicianInd.setName(objectupdate.getName());
		technicianInd.setCost(objectupdate.getCost());
		technicianInd.setTelephone(objectupdate.getTelephone());
		technicianInd.setStatus(objectupdate.getStatus());
		technicianInd.setSpecialties(objectupdate.getSpecialties());
		technicianIndRepository.save(technicianInd);	
		return technicianInd;
	}

	@Override
	public void delete(Long objectId) {
		technicianIndRepository.delete(findById(objectId));
	}

	@Override
	public TechnicianInd findById(Long id) {
		Optional<TechnicianInd> technicianInd = technicianIndRepository.findById(id);

		if (!technicianInd.isPresent()) {
            throw new ResourceNotFoundException("There is no TechnicianInd with ID = " + id);
        }

		return technicianInd.get();
	}

	@Override
	public TechnicianInd getLatestEntry() {
		 List<TechnicianInd> technicianInd = getAll();
	        if(technicianInd.isEmpty()){
	            return null;
	        }
	        else{
	            Long latestTechnicianIndID = technicianIndRepository.findTopByOrderByIdDesc();
	            return findById(latestTechnicianIndID);
	        }
	}

	@Override
	public Page<TechnicianInd> findAll(Pageable pageable) {
		return technicianIndRepository.findAll(pageable);
	}

	@Override
	public boolean TechnicianIndValid(TechnicianInd technicianInd) {
		List<TechnicianInd> techniciansInd= new ArrayList<>();
		technicianIndRepository.findByTechnicianIndName(technicianInd.getName())
                .iterator().forEachRemaining(techniciansInd::add);
        if (!techniciansInd.isEmpty()) { return false;}
        else {return true;}
	}

	
}
