package com.example.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.example.entity.Account;

import javax.mail.MessagingException;

public interface AccountService {
  public List<Account> findAll();

  public Account findById(String username);

  public List<Account> getAdministrators();

  public Account update(Account account);

  public void deleteById(String username);

  public Account create(Account account);

  public Account findByUserName(String userName);

  public void sendEmail(String recipientEmail, String link)
      throws MessagingException, UnsupportedEncodingException;

  public Boolean verify(String verificationCode);

  public void sendVerificationEmail(Account account, String siteURL) throws MessagingException, UnsupportedEncodingException;

  public void registerVerify(Account account, String siteURL) throws UnsupportedEncodingException, MessagingException;

  public Account getByResetPasswordToken(String token);

  public void updatePassword(Account account, String newPassword);

  public void updateResetPasswordToken(String token, String email);

}
