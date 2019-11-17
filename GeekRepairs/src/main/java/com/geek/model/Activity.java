package com.geek.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import com.geek.dateAudit.DateAudit;


@Entity
@Table(name="activities")
public class Activity extends DateAudit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name ="act_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Por favor ingrese un nombre")
	@Column(name = "name")
	private String name;

	@NotEmpty(message = "Por favor ingrese una descripcion")
	@Type(type = "org.hibernate.type.TextType")
	private String description;
	
	@NotEmpty(message = "Por favor ingrese un estado")
	@Column(name = "status")
	private String state;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "startDate", nullable = false)
	private Date startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "endingDate")
	private Date endingDate;
	


	public Activity() {
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());	
	}
	

	public Activity(String name,String description, Date startDate,Date endingDate,
			 String state) {
		this.name = name;
		this.description=description;
		this.endingDate=endingDate;
		this.startDate=startDate;
		this.state=state;

		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());	
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndingDate() {
		return endingDate;
	}


	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}
	
	
	
}
