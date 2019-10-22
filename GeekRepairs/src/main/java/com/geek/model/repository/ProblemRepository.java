package com.geek.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geek.model.entity.Problem;

@Repository
public interface ProblemRepository extends JpaRepository<Problem,Long> {

}
