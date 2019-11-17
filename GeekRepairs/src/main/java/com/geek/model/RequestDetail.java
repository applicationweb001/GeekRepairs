package com.geek.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "request_detail")
public class RequestDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "quantity",nullable = false)
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product productid;

	
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProductid() {
		return productid;
	}

	public void setProductid(Product productid) {
		this.productid = productid;
	}
	
	public double calculateAmount() {
		return quantity * productid.getUnitPrice();
	}
	
}
