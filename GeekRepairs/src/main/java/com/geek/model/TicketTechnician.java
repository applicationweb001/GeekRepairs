package com.geek.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.geek.dateAudit.DateAudit;

@Entity
@Table(name="ticket_technician")
public class TicketTechnician extends DateAudit {

	@Id
	@Column(name = "ticket_technician_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "technician_id")
	private TechnicianInd technician;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TechnicianInd getTechnician() {
		return technician;
	}

	public void setTechnician(TechnicianInd technician) {
		this.technician = technician;
	}
	
	
	
	
	
	
}
