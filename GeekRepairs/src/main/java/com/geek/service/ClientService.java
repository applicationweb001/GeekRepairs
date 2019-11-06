package com.geek.service;


import com.geek.model.Client;

public interface ClientService extends CrudService<Client, Long> {
	
	boolean ClientValid(Client client);
	

}
