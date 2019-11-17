package com.geek.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.geek.exception.ResourceNotFoundException;
import com.geek.model.Activity;
import com.geek.model.Adviser;
import com.geek.repository.ActivityRepository;
import com.geek.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository actRepository;

	
	
	@Override
	public List<Activity> getAll() {
		List<Activity> act= new ArrayList<>();
		actRepository.findAll().iterator().forEachRemaining(act::add);
		return act;
	}

	@Override
	public Activity create(Activity object) {
		Activity newAct;
		newAct = actRepository.save(object);
		return newAct;
	}

	@Override
	public Activity update(Long id, Activity objectupdate) {
		Activity act= findById(id);

		act.setDescription(objectupdate.getDescription());
		act.setEndingDate(objectupdate.getEndingDate());
		act.setStartDate(objectupdate.getStartDate());
		act.setName(objectupdate.getName());
		act.setState(objectupdate.getState());
		

		actRepository.save(act);
		return act;
	}

	@Override
	public void delete(Long objectId) {
		actRepository.delete(findById(objectId));
		
	}

	@Override
	public Activity findById(Long id) {
		Optional<Activity> act= actRepository.findById(id);

		if (!act.isPresent()) {
			throw new ResourceNotFoundException("No hay actividades con ID = " + id);
		}

		return act.get();
	}

	@Override
	public Activity getLatestEntry() {
		 List<Activity> act= getAll();
	        if(act.isEmpty()){
	            return null;
	        }
	        else{
	            Long latestActIndID = actRepository.findTopByOrderByIdDesc();
	            return findById(latestActIndID);
	        }
	}

	@Override
	public Page<Activity> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return actRepository.findAll(pageable);
	}

	@Override
	public boolean ActivityInValid(Activity act) {
		List<Activity> acts= new ArrayList<>();
		actRepository.findByActivityIndName(act.getName())
                .iterator().forEachRemaining(acts::add);
        if (!acts.isEmpty()) { return false;}
        else {return true;}
	}

	
}
