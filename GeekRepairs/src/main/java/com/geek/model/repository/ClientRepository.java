package com.geek.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geek.model.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	
}