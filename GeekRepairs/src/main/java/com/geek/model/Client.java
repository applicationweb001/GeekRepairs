package com.geek.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.geek.dateAudit.DateAudit;

@Entity
@Table(name="clients")
public class Client extends DateAudit {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "client_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Ingresar nombre del Cliente")
	@Column(name="name")
	private String name;
	
	@NotEmpty(message="Ingresar Apellido del Cliente")
	@Column(name="surname")
	private String surname;
	
	@NotEmpty(message="Ingresar nombre del email")
	@Column(name="email")
	private String email;
	
	@NotEmpty(message="Ingresar Celular del Cliente")
	@Column(name="phone")
	private String phone;
	
	@NotEmpty(message="Ingresar Direccion del Cliente")
	@Column(name="address")
	private String address;
	
	private String type;
	
		
	
	public Client() {
    	this.setCreatedAt(new Date());
        this.setUpdatedAt(new Date());
    }
	
	 public Client(@Size(min = 3, max = 100) String title, @NotEmpty String name) {
	        this.name = name;
	       
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
	 

}