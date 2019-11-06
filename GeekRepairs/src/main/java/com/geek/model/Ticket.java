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

import ch.qos.logback.core.net.server.Client;

@Entity
@Table(name = "tickets")
public class Ticket extends DateAudit {

	@Id
	@Column(name = "ticket_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
/*
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
*/
	@NotEmpty(message = "Please enter a date attention")
	@Column(name = "dateAttention", nullable = false)
	private String dateAttention;

	@NotEmpty(message = "Please enter a date service")
	@Column(name = "dateService", nullable = false)
	private String dateService;

	@NotEmpty(message = "Please enter a status")
	@Column(name = "typeService")
	private String typeService;

	@NotEmpty(message = "Please enter a status")
	private String status;

	@NotEmpty(message = "Please enter a address.")
	private String address;

	@NotEmpty(message = "Please enter a startTime")
	private String startTime;

	public Ticket() {
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	public Ticket(@NotEmpty String dateAttention, @NotEmpty String dateService, @NotEmpty String typeService,
			@NotEmpty String status, @NotEmpty String address, @NotEmpty String startTime) {
		this.dateAttention = dateAttention;
		this.dateService = dateService;
		this.typeService = typeService;
		this.status = status;
		this.address = address;
		this.startTime = startTime;

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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	
}
