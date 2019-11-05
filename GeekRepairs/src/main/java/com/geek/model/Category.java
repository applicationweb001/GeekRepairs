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
@Table(name = "categories")
public class Category extends DateAudit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message="Please enter a category.")
	@Column(name="name")
	private String name;

	public Category() {
    	this.setCreatedAt(new Date());
        this.setUpdatedAt(new Date());
    }

    public Category(@Size(min = 2, max = 100) String title, @NotEmpty String name) {
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


}