package com.geek.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.geek.dateAudit.DateAudit;

@Entity
@Table(name="advisors")
public class Adviser extends DateAudit  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name ="adv_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Por favor ingrese su nombre")
	@Column(name = "name")
	private String name;
	
	@NotEmpty(message = "Por favor ingrese su apellido")
	@Column(name = "surname")
	private String surname;
	
	
	@NotEmpty(message = "Por favor ingrese su email")
	@Column(name = "email")
	private String email;
	
	@NotEmpty(message = "Por favor ingrese su telefono")
	@Column(name = "mobile")
	private String mobile;


	public Adviser() {
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());	
	}
	

	public Adviser(@NotEmpty String name, @NotEmpty String telephone, @NotEmpty String email,
			@NotEmpty String surname) {
		this.name = name;
		this.mobile = telephone;
		this.email = email;
		this.surname = surname;

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
}
