
package com.example.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.example.service.UserDetailService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dao.AccountDAO;
import com.example.entity.Account;
import com.example.service.AccountService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class AccountServiceImpl implements AccountService {

  @Autowired
  AccountDAO adao;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JavaMailSender mailSender;

  public void registerVerify(Account account, String siteURL) throws UnsupportedEncodingException, MessagingException {
    String randomCode = RandomString.make(64);
    account.setVerificationCode(randomCode);
    account.setIsActive(false);
    adao.save(account);
    sendVerificationEmail(account, siteURL);
  }

  public void sendVerificationEmail(Account account, String siteURL) throws MessagingException, UnsupportedEncodingException {
    String toAddress = account.getEmail();
    String fromAddress = "nhut23112000@gmail.com";
    String senderName = "DinhNhut2k Shoes Company";
    String subject = "Please verify your registration";
    String content = "Dear [[name]],<br>"
        + "Please click the link below to verify your registration:<br>"
        + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
        + "Thank you, have a good day<br>"
        + "Runner Shop.";

    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);

    helper.setFrom(fromAddress, senderName);
    helper.setTo(toAddress);
    helper.setSubject(subject);

    content = content.replace("[[name]]", account.getFullName());
    String verifyURL = siteURL + "/verify?code=" + account.getVerificationCode();
    content = content.replace("[[URL]]", verifyURL);
    helper.setText(content, true);
    mailSender.send(message);
  }


  public Boolean verify(String verificationCode) {
    Account account = adao.findByVerificationCode(verificationCode);

    if (account == null || account.getIsActive()) {
      return false;
    } else {
      account.setVerificationCode(null);
      account.setIsActive(true);
      adao.save(account);

      return true;
    }
  }

  public void updateResetPasswordToken(@RequestBody String token, String email) throws UsernameNotFoundException {
    System.out.println(email);
    Account account = adao.findByEmail(email);
    System.out.println(account);
    if (account != null) {
      account.setResetPasswordToken(token);
      adao.save(account);
    } else {
      throw new UsernameNotFoundException("Could not find any customer with the email " + email);
    }
  }
  public void changePassword(String userName,String currentPassword,String newPassword)throws UsernameNotFoundException{
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode(newPassword);
    Account account = adao.findUserByUserName(userName);
    if(account.getPassword().equals(currentPassword)){
      throw new UsernameNotFoundException("Your current password is invalid!");
    }
    account.setPassword(encodedPassword);
    adao.save(account);
  }


  public Account getByResetPasswordToken(String token) {
    return adao.findByResetPasswordToken(token);
  }

  public void updatePassword(Account account, String newPassword) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode(newPassword);
    account.setPassword(encodedPassword);
    account.setResetPasswordToken(null);
    adao.save(account);
  }

  public void sendEmail(String recipientEmail, String link)
      throws MessagingException, UnsupportedEncodingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);

    helper.setFrom("nhut23112000@gmail.com", "RunnerShop Support");
    helper.setTo(recipientEmail);

    String subject = "Here's the link to reset your password";

    String content = "<p>Hello,</p>"
        + "<p>You have requested to reset your password.</p>"
        + "<p>Click the link below to change your password:</p>"
        + "<p><a href=\"" + link + "\">Change my password</a></p>"
        + "<br>"
        + "<p>Ignore this email if you do remember your password, "
        + "or you have not made the request.</p>";

    helper.setSubject(subject);

    helper.setText(content, true);

    mailSender.send(message);
  }
  @Override
  public Account findById(String username) {
    return adao.findById(username).get();
  }

  @Override
  public List<Account> findAll() {
    return adao.findAll();
  }

  @Override
  public List<Account> getAdministrators() {
    return adao.getAdministrators();
  }

  @Override
  public Account update(Account account) {
    return adao.save(account);
  }

  @Override
  public void deleteById(String username) {
    adao.deleteById(username);
  }

  @Override
  public Account create(Account account) {
    account.setIsActive(true);
    return adao.save(account);
  }

  @Override
  public Account findByUserName(String userName) {
    return adao.findByUserName(userName);
  }


}

