package com.example.service.impl;

import com.example.dao.AccountDAO;
import com.example.entity.Account;
import com.example.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
  @Autowired
  AccountDAO accountDAO;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account acc = accountDAO.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailService.build(acc);
  }
}
