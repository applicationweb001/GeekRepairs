package com.ross.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ross.model.entity.Problem;

@Repository
public interface ProblemRepository extends JpaRepository<Problem,Long> {

}
