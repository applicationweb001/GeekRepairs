package com.geek.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geek.model.Account;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {
	 Account findByUserName(String userName);
}
