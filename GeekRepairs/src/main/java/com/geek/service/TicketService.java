package com.geek.service;


import com.geek.model.Ticket;

public interface TicketService extends CrudService<Ticket,Long>{
	boolean TicketValid(Ticket ticket);
}
