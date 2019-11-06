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
@Table(name = "specialties")
public class Specialty extends DateAudit{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "specialty_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message="Please enter a name.")
	@Column(name="name")
	private String name;
	

	@NotEmpty(message="Please enter a description.")
	@Column(name="description")
	private String description;

	public Specialty() {
    	this.setCreatedAt(new Date());
        this.setUpdatedAt(new Date());
    }

    public Specialty(@NotEmpty String name,@NotEmpty String description) {
        this.name = name;
        this.description = description;
       
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
