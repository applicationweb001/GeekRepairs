package com.hampcode.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hampcode.model.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	
}