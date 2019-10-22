package com.geek.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geek.model.entity.TechnicianInd;
import com.geek.model.repository.TechnicianIndRepository;
import com.geek.service.TechnicianIndService;

@Service
public class TechnicianIndlmpl implements TechnicianIndService{
	@Autowired
	private TechnicianIndRepository technicianIndRepository;
	
	@Override
	public List<TechnicianInd> getAll() {
		// TODO Auto-generated method stub
		return technicianIndRepository.findAll();
	}

	@Override
	public TechnicianInd getOneById(Long id) {
		// TODO Auto-generated method stub
		return technicianIndRepository.findById(id).orElseThrow(()-> new RuntimeException("Technician Not Found!"));
	}

	@Override
	public Long create(TechnicianInd entity) {
		// TODO Auto-generated method stub
		technicianIndRepository.save(entity);
		return entity.getId();
	}

	@Override
	public void update(Long id, TechnicianInd entity) {
		// TODO Auto-generated method stub
		TechnicianInd currentTechnician = getOneById(id);
		currentTechnician.setName(entity.getName());
		currentTechnician.setCost(entity.getCost());
		currentTechnician.setTelephone(entity.getTelephone());
		currentTechnician.setStatus(entity.getStatus());
		technicianIndRepository.save(currentTechnician);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		technicianIndRepository.deleteById(id);		
	}
	
	

}
