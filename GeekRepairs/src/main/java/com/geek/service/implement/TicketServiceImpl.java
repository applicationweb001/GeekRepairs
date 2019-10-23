package com.geek.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geek.model.entity.Ticket;
import com.geek.model.repository.TicketRepository;
import com.geek.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public List<Ticket> getAll() {
		return ticketRepository.findAll();
	}

	@Override
	public Ticket getOneById(Long id) {
		return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Category Not Found!"));
	}

	@Override
	public Long create(Ticket entity) {
		
		entity.setStatus("Pendiente");
		ticketRepository.save(entity);
		return entity.getId();
	}

	@Override
	public void update(Long id, Ticket entity) {
		Ticket currentTicket = getOneById(id);
		currentTicket.setAddress(entity.getAddress());
		currentTicket.setDateAttention(entity.getDateAttention());
		currentTicket.setDateService(entity.getDateService());
		currentTicket.setClient(entity.getClient());
		currentTicket.setStartTime(entity.getStartTime());
		currentTicket.setStatus(entity.getStatus());
		currentTicket.setTypeService(entity.getTypeService());
		ticketRepository.save(currentTicket);
	}

	@Override
	public void delete(Long id) {
		ticketRepository.deleteById(id);
	}

}
