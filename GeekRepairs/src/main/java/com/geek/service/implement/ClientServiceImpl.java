package com.geek.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.geek.exception.ResourceNotFoundException;
import com.geek.model.Client;
import com.geek.repository.ClientRepository;
import com.geek.service.ClientService;
@Service

public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository clientRepository;
		

	@Override
	public List<Client> getAll() {
		List<Client> clients= new ArrayList();
		clientRepository.findAll().iterator().forEachRemaining(clients::add);
		return clients;
	}
	
	

	@Override
	public Client create(Client object) {
		Client newclient;
		newclient=clientRepository.save(object);
		return newclient;
	}

	@Override
	public Client update(Long id, Client objectupdate) {
		Client client=findById(id);
		
		client.setName(objectupdate.getName());
		clientRepository.save(client);
		return client;
	}

	@Override
	public void delete(Long id) {
		clientRepository.delete(findById(id));
		
	}

	@Override
	public Client findById(Long id) {
		Optional<Client> client=clientRepository.findById(id);
		
		if (!client.isPresent()) {
            throw new ResourceNotFoundException("There is no Client with ID = " + id);
        }
		
		return client.get();
	}

	@Override
	public Client getLatestEntry() {
		List<Client> client=getAll();
		
		if(client.isEmpty()) {
			return null;
			
		}else {
			Long latestClientID=clientRepository.findTopByOrderByIdDesc();
			return findById(latestClientID);
		}
		
	}

	@Override
	public Page<Client> findAll(Pageable pageable) {
		return clientRepository.findAll(pageable);
	}

	@Override
	public boolean ClientValid(Client client) {
		// TODO Auto-generated method stub
		List<Client> clients=new ArrayList<>();
		clientRepository.findByClientName(client.getName())
		.iterator().forEachRemaining(clients::add);
		if(!clients.isEmpty()) {return false;}
		else {return true;}
		
		
	}

}