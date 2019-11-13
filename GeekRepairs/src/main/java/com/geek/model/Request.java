package com.geek.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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


	@NotEmpty(message = "Please enter a quantity")
	@Column(name="quantity")
	private String quantity;
	

	@NotEmpty(message = "Please enter a price")
	@Column(name ="total_price")
	private String price;

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="Request_Product",joinColumns= {@JoinColumn(name="request_id")}) // aqui se agrego
	@NotEmpty(message = "Please enter a Product")
	private List<Product> products = new ArrayList<>();

	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Request() {
    	this.setCreatedAt(new Date());
        this.setUpdatedAt(new Date());
    }

    public Request(@NotEmpty String quantity,@NotEmpty String price) {
        this.quantity = quantity;
        this.price = price;
       
        
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
