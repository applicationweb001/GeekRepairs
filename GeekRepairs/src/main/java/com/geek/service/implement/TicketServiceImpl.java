package com.geek.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.geek.exception.ResourceNotFoundException;
import com.geek.model.Ticket;
import com.geek.repository.TicketRepository;
import com.geek.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public List<Ticket> getAll() {
		List<Ticket> tickets = new ArrayList<>();
		ticketRepository.findAll().iterator().forEachRemaining(tickets::add);
		return tickets;
	}

	@Override
	public Ticket create(Ticket object) {
		Ticket newticket;
		newticket = ticketRepository.save(object);
		return newticket;
	}

	@Override
	public Ticket update(Long id, Ticket objectupdate) {
		Ticket ticket = findById(id);

		ticket.setAddress(objectupdate.getAddress());
		ticket.setClient(objectupdate.getClient());
		ticket.setDateAttention(objectupdate.getDateAttention());
		ticket.setDateService(objectupdate.getDateService());
		ticket.setTypeService(objectupdate.getTypeService());
		ticket.setStatus(objectupdate.getStatus());
		//ticket.setStartTime(objectupdate.getStartTime());

		ticketRepository.save(ticket);
		return ticket;
	}

	@Override
	public void delete(Long objectId) {
		ticketRepository.delete(findById(objectId));

	}

	@Override
	public Ticket findById(Long id) {
		Optional<Ticket> ticket = ticketRepository.findById(id);

		if (!ticket.isPresent()) {
            throw new ResourceNotFoundException("There is no Ticket with ID = " + id);
        }

		return ticket.get();
	}

	@Override
	public Ticket getLatestEntry() {
		 List<Ticket> ticket = getAll();
	        if(ticket.isEmpty()){
	            return null;
	        }
	        else{
	            Long latestTicketID = ticketRepository.findTopByOrderByIdDesc();
	            return findById(latestTicketID);
	        }
	}

	@Override
	public Page<Ticket> findAll(Pageable pageable) {
		 return ticketRepository.findAll(pageable);
	}

	@Override
	public boolean TicketValid(Ticket ticket) {
		List<Ticket> tickets= new ArrayList<>();
		ticketRepository.findByTicketId(ticket.getId())
                .iterator().forEachRemaining(tickets::add);
        if (!tickets.isEmpty()) { return false;}
        else {return true;}
	}
}
