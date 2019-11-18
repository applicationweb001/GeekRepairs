package com.geek.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.geek.exception.ResourceNotFoundException;
import com.geek.model.Satisfaction;
import com.geek.repository.SatisfactionRepository;
import com.geek.service.SatisfactionService;

@Service
public class SatisfactionServiceImpl implements SatisfactionService {
	@Autowired
	private SatisfactionRepository satisfactionRepository;

	@Override
	public List<Satisfaction> getAll() {
		List<Satisfaction> satisfactions = new ArrayList<>();
		satisfactionRepository.findAll().iterator().forEachRemaining(satisfactions::add);
		return satisfactions;
	}

	@Override
	public Satisfaction create(Satisfaction object) {
		Satisfaction newsatisfaction;
		newsatisfaction = satisfactionRepository.save(object);
		return newsatisfaction;
	}

	@Override
	public Satisfaction update(Long id, Satisfaction objectupdate) {
		Satisfaction satisfaction = findById(id);
		satisfaction.setExperience(objectupdate.getExperience());;
		satisfaction.setProfessionalism(objectupdate.getProfessionalism());
		satisfaction.setCompression(objectupdate.getCompression());
		satisfaction.setQuality(objectupdate.getQuality());
		satisfaction.setTime(objectupdate.getTime());
		satisfaction.setTicket(objectupdate.getTicket());
		satisfactionRepository.save(satisfaction);	
		return satisfaction;
	}

	@Override
	public void delete(Long objectId) {
		satisfactionRepository.delete(findById(objectId));
	}

	@Override
	public Satisfaction findById(Long id) {
		Optional<Satisfaction> satisfaction = satisfactionRepository.findById(id);

		if (!satisfaction.isPresent()) {
            throw new ResourceNotFoundException("No hay registro de satisfacción un este id = " + id);
        }

		return satisfaction.get();
	}

	@Override
	public Satisfaction getLatestEntry() {
		List<Satisfaction> satisfaction = getAll();
        if(satisfaction.isEmpty()){
            return null;
        }
        else{
            Long latestTechnicianIndID = satisfactionRepository.findTopByOrderByIdDesc();
            return findById(latestTechnicianIndID);
        }
	}

	@Override
	public Page<Satisfaction> findAll(Pageable pageable) {
		return satisfactionRepository.findAll(pageable);
	}

}
