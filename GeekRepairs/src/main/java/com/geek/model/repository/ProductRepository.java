package com.geek.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geek.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
