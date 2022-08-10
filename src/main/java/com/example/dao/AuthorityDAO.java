package com.example.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Account;
import com.example.entity.Authority;
public interface AuthorityDAO extends JpaRepository<Authority, Integer>{
	@Query(value="SELECT DISTINCT * FROM Authorities  WHERE RoleId IN('DIRE','STAF')", nativeQuery = true)
	List<Authority> authoritiesOf(List<Account> accounts);
    
}
