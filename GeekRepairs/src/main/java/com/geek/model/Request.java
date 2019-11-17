package com.geek.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.geek.dateAudit.DateAudit;

@Entity
@Table(name = "requests")
public class Request extends DateAudit{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "request_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "ticket_id")
	public Ticket ticketId;
	
	public Ticket getTicketid() {
		return ticketId;
	}


	public void setTicketid(Ticket ticketid) {
		this.ticketId = ticketid;
	}

	@OneToMany(fetch= FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "requestdetail_id")
	private List<RequestDetail> requestDetail;
		
	
	public Request() {
		requestDetail = new ArrayList<>();
		
    	this.setCreatedAt(new Date());
        this.setUpdatedAt(new Date());
    }


	public List<RequestDetail> getRequestDetail() {
		return requestDetail;
	}



	public void setRequestDetail(List<RequestDetail> requestDetail) {
		this.requestDetail = requestDetail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void addRequestDetail(RequestDetail item) {
		this.requestDetail.add(item);
	}
	
	public double getTotal() {
		return requestDetail.stream().collect(Collectors.summingDouble(RequestDetail::calculateAmount));
	}
	
	
}
