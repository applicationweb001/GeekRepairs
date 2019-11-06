package com.geek.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


import com.geek.dateAudit.DateAudit;


@Entity
@Table(name = "tickets")
public class Ticket extends DateAudit {

	@Id
	@Column(name = "ticket_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "client_id")
	@NotEmpty(message = "Please enter a Client")
	private Client client;
	
	@NotEmpty(message = "Please enter a date attention")
	@Column(name = "dateAttention", nullable = false)
	private String dateAttention;

	@Column(name = "dateService", nullable = false)
	private String dateService;

	@NotEmpty(message = "Please enter a typeservice")
	@Column(name = "typeService")
	private String typeService;

	@NotEmpty(message = "Please enter a status")
	private String status;

	@NotEmpty(message = "Please enter a address.")
	private String address;


	public Ticket() {
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}
	
	public Ticket(Long id, @NotEmpty(message = "Please enter a Client") Client client,
			@NotEmpty(message = "Please enter a date attention") String dateAttention, String dateService,
			@NotEmpty(message = "Please enter a typeservice") String typeService,
			@NotEmpty(message = "Please enter a status") String status,
			@NotEmpty(message = "Please enter a address.") String address) {
		super();
		this.id = id;
		this.client = client;
		this.dateAttention = dateAttention;
		this.dateService = dateService;
		this.typeService = typeService;
		this.status = status;
		this.address = address;
		
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
/*
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
*/
	public String getDateAttention() {
		return dateAttention;
	}

	public void setDateAttention(String dateAttention) {
		this.dateAttention = dateAttention;
	}

	public String getDateService() {
		return dateService;
	}

	public void setDateService(String dateService) {
		this.dateService = dateService;
	}

	public String getTypeService() {
		return typeService;
	}

	public void setTypeService(String typeService) {
		this.typeService = typeService;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
}
