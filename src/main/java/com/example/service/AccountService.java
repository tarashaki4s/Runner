package com.example.service;

import java.util.List;

import com.example.entity.Account;

public interface AccountService {
	public List<Account> findAll();

	public Account findById(String username);

	public List<Account> getAdministrators();

	public Account update(Account account);

	public void deleteById(String username);

	public Account create(Account account);

	public Account findByUserName(String userName);

}
