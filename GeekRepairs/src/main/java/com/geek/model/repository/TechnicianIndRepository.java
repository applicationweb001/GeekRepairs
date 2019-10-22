package com.geek.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geek.model.entity.TechnicianInd;

@Repository
public interface TechnicianIndRepository extends JpaRepository<TechnicianInd,Long>{

}
