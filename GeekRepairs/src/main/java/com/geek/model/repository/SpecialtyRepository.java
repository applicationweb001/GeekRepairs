package com.geek.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hampcode.model.entity.Specialty;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long>{

}
