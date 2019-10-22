package com.geek.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.geek.model.entity.TecRemote;
import com.geek.model.repository.TecRemoteRepository;
import com.geek.service.TecRemoteService;

@Service
public class TecRemoteServiceImpl implements TecRemoteService {

	@Autowired
	private TecRemoteRepository tecRemoteRepository;
	
	@Override
	public List<TecRemote> getAll() {
		
		return tecRemoteRepository.findAll();
	}

	@Override
	public TecRemote getOneById(Long id) {
		// TODO Auto-generated method stub
		return tecRemoteRepository.findById(id).orElseThrow(()->new RuntimeException("tecRemote not found!"));
	}

	@Override
	public Long create(TecRemote entity) {
		// TODO Auto-generated method stub
		tecRemoteRepository.save(entity);
		return entity.getId();
	}

	@Override
	public void update(Long id, TecRemote entity) {
		// TODO Auto-generated method stub
		TecRemote currenttecRemote= getOneById(id);
		currenttecRemote.setName(entity.getName());
		currenttecRemote.setSurname(entity.getSurname());
		currenttecRemote.setEmail(entity.getEmail());
		currenttecRemote.setMobile(entity.getMobile());
	
		tecRemoteRepository.save(currenttecRemote);
	}

	@Override
	public void delete(Long id) {
		tecRemoteRepository.deleteById(id);
		
	}

}
