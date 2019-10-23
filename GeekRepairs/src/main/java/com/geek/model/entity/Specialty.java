package com.geek.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name = "specialties")
public class Specialty {
	@Id
	@Column(name = "specialty_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@NotEmpty
	private String name;

	@NotEmpty
	private String description;

	@ManyToMany(mappedBy = "specialties")
	private List<TechnicianInd> TechniciansInd;
	
	public Specialty() {
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

	public List<TechnicianInd> getTechniciansInd() {
		return TechniciansInd;
	}

	public void setTechniciansInd(List<TechnicianInd> techniciansInd) {
		TechniciansInd = techniciansInd;
	}



}