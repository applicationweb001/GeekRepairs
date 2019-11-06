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
@Table(name = "requests")
public class Request extends DateAudit{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "request_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message="Please enter a product.")
	@Column(name="product")
	private String product;
	

	@Column(name="quantity")
	private int quantity;
	
	@Column(name ="total_price")
	private double price;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Request() {
    	this.setCreatedAt(new Date());
        this.setUpdatedAt(new Date());
    }

    public Request(@NotEmpty String product,@NotEmpty int quantity,@NotEmpty double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
       
        this.setCreatedAt(new Date());
        this.setUpdatedAt(new Date());
        
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
