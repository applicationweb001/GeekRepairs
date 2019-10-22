package com.geek.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geek.model.entity.TecRemote;

@Repository
public interface TecRemoteRepository extends JpaRepository<TecRemote,Long> {

}
