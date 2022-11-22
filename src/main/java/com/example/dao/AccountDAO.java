package com.example.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO extends JpaRepository<Account, String>{
	Optional<Account> findByUsername(String username);
	Boolean existsByUsername(String userName);
	Boolean existsByEmail(String email);
//	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN('DIRE','STAF')")
//	List<Account> getAdministrators();

	@Query("SELECT o FROM Account o WHERE o.Username=?1")
	List<Account> findByUserName(String userName);
    
}
