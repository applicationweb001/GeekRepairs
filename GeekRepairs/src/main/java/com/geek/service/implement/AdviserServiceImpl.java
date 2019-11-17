package com.geek.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.geek.exception.ResourceNotFoundException;
import com.geek.model.Adviser;

import com.geek.repository.AdviserRepository;
import com.geek.service.AdviserService;

@Service
public class AdviserServiceImpl implements AdviserService {

	@Autowired
	private AdviserRepository tecRepository;

	@Override
	public List<Adviser> getAll() {
		List<Adviser> tecremote= new ArrayList<>();
		tecRepository.findAll().iterator().forEachRemaining(tecremote::add);
		return tecremote;
	}

	@Override
	public Adviser create(Adviser entity) {
		Adviser newTec;
		newTec = tecRepository.save(entity);
		return newTec;
	}

	@Override
	public Adviser update(Long id, Adviser entity) {
		Adviser tecremote= findById(id);

		tecremote.setEmail(entity.getEmail());
		tecremote.setName(entity.getName());
		tecremote.setSurname(entity.getSurname());
		tecremote.setMobile(entity.getMobile());
		

		tecRepository.save(tecremote);
		return tecremote;
	}

	@Override
	public void delete(Long id) {
		tecRepository.delete(findById(id));
	}

	@Override
	public Adviser findById(Long id) {
		Optional<Adviser> article = tecRepository.findById(id);

		if (!article.isPresent()) {
			throw new ResourceNotFoundException("No hay tecnico remoto con ID = " + id);
		}

		return article.get();

	}

	@Override
	public Page<Adviser> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return tecRepository.findAll(pageable);
	}

	@Override
	public Adviser getLatestEntry() {
		 List<Adviser> technician= getAll();
	        if(technician.isEmpty()){
	            return null;
	        }
	        else{
	            Long latestTechnicianIndID = tecRepository.findTopByOrderByIdDesc();
	            return findById(latestTechnicianIndID);
	        }
	}


	@Override
	public boolean AdviserIndValid(Adviser tec) {
		List<Adviser> technicians= new ArrayList<>();
		tecRepository.findByTechnicianIndName(tec.getName())
                .iterator().forEachRemaining(technicians::add);
        if (!technicians.isEmpty()) { return false;}
        else {return true;}
	}


}
