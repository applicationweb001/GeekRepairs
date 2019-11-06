package com.geek.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.geek.exception.ResourceNotFoundException;
import com.geek.model.TecRemote;

import com.geek.repository.TecRemoteRepository;
import com.geek.service.TecRemoteService;

@Service
public class TecRemoteServiceImpl implements TecRemoteService {

	@Autowired
	private TecRemoteRepository tecRepository;

	@Override
	public List<TecRemote> getAll() {
		List<TecRemote> tecremote= new ArrayList<>();
		tecRepository.findAll().iterator().forEachRemaining(tecremote::add);
		return tecremote;
	}

	@Override
	public TecRemote create(TecRemote entity) {
		TecRemote newTec;
		newTec = tecRepository.save(entity);
		return newTec;
	}

	@Override
	public TecRemote update(Long id, TecRemote entity) {
		TecRemote tecremote= findById(id);

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
	public TecRemote findById(Long id) {
		Optional<TecRemote> article = tecRepository.findById(id);

		if (!article.isPresent()) {
			throw new ResourceNotFoundException("No hay tecnico remoto con ID = " + id);
		}

		return article.get();

	}

	@Override
	public Page<TecRemote> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return tecRepository.findAll(pageable);
	}

	@Override
	public TecRemote getLatestEntry() {
		 List<TecRemote> technician= getAll();
	        if(technician.isEmpty()){
	            return null;
	        }
	        else{
	            Long latestTechnicianIndID = tecRepository.findTopByOrderByIdDesc();
	            return findById(latestTechnicianIndID);
	        }
	}

	@Override
	public boolean TechnicianIndValid(TecRemote technician) {
		List<TecRemote> technicians= new ArrayList<>();
		tecRepository.findByTechnicianIndName(technician.getName())
                .iterator().forEachRemaining(technicians::add);
        if (!technicians.isEmpty()) { return false;}
        else {return true;}
	}


}
