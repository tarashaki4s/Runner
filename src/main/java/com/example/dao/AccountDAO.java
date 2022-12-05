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
	@Query("SELECT u FROM Account u WHERE u.verificationCode = ?1")
	Account findByVerificationCode(String code);
	@Query(value=("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN('ROLE_USER','ROLE_ADMIN')"),nativeQuery = true)
	List<Account> getAdministrators();

	@Query("SELECT o FROM Account o WHERE o.username=?1")
	List<Account> findByUserName(String userName);

	@Query("SELECT o FROM Account o WHERE o.username=?1")
	Account findUserByUserName(String userName);
	@Query(value = ("SELECT TOP 1 * FROM Accounts o WHERE o.email = ?1"),nativeQuery = true)
	Account findByEmail(String email);

	public Account findByResetPasswordToken(String token);
	@Query("select a from Account a where a.username=?1 and a.password=?2")
	Account findByUserNameAndPassword(String username,String password);

}
