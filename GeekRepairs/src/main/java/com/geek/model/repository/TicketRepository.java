package com.geek.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.geek.model.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{

	
}
