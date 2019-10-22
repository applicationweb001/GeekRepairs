package com.hampcode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hampcode.model.entity.Client;
import com.hampcode.model.repository.ClientRepository;
import com.hampcode.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public List<Client> getAll() {
		
		return clientRepository.findAll();
	}

	@Override
	public Client getOneById(Long id) {
		
		return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client Not Found!"));
	}

	@Override
	public Long create(Client entity) {
		clientRepository.save(entity);
		return entity.getId();
	}

	@Override
	public void update(Long id, Client entity) {
		Client currentClient=getOneById(id);
		currentClient.setName(entity.getName());
		currentClient.setSurname(entity.getSurname());
		currentClient.setAddress(entity.getAddress());
		currentClient.setEmail(entity.getEmail());
		currentClient.setType(entity.getType());
		clientRepository.save(currentClient);
		
	}

	@Override
	public void delete(Long id) {
		clientRepository.deleteById(id);
		
	}
	
	

}