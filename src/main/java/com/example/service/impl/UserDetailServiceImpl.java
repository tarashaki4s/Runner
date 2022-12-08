package com.example.service.impl;

import com.example.dao.AccountDAO;
import com.example.entity.Account;
import com.example.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
  @Autowired
  AccountDAO accountDAO;
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account acc = accountDAO.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    if(!acc.getIsActive()){
      System.out.println("Your accout is not verify! Please check your email");

    }else{

    }

    return UserDetailService.build(acc);
  }
}
