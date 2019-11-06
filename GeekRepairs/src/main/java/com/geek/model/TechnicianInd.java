package com.geek.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.geek.dateAudit.DateAudit;

@Entity
@Table(name = "techniciansInd")
public class TechnicianInd extends DateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "technicianInd_id")
	private long id;

	@NotEmpty(message = "Por favor ingrese un nombre")
	@Column(name = "name")
	private String name;

	@NotEmpty(message = "Por favor ingrese un telefono")
	@Column(name = "telephone")
	private String telephone;

	@NotEmpty(message = "Por favor ingrese un costo")
	@Column(name = "cost")
	private String cost;

	@NotEmpty(message = "Por favor ingrese un estado")
	@Column(name = "status")
	private String status;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="technicianInd_specialty",joinColumns= {@JoinColumn(name="technicianInd_id")}) // aqui se agrego
	@NotEmpty(message = "Por favor ingrese al menos una especialidad para el tecnico")
	private List<Specialty> specialties = new ArrayList<>();

	public TechnicianInd() {
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	public TechnicianInd(@NotEmpty String name, @NotEmpty String telephone, @NotEmpty String cost,
			@NotEmpty String status) {
		this.name = name;
		this.telephone = telephone;
		this.cost = cost;
		this.status = status;

		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Specialty> getSpecialties() {
		return specialties;
	}

	public void setSpecialties(List<Specialty> specialties) {
		this.specialties = specialties;
	}
	
	

	
}
