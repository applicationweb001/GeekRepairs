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
@Table(name = "products")
public class Product extends DateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Please enter a product")
	@Column(name = "name")
	private String name;

	@NotEmpty(message = "Please enter a price")
	@Column(name = "unite_price")
	private String unitPrice;

	public Product() {
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	public Product(@NotEmpty String name, @NotEmpty String unitPrice) {
		this.name = name;
		this.unitPrice = unitPrice;
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

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
