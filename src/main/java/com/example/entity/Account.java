package com.example.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Accounts")
public class Account implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "Username")
  private String username;

  @Column(name = "Password")
  private String password;

  @Column(name = "Email")
  private String email;

  @Column(name = "Fullname")
  private String fullName;

  @Column(name = "Gender")
  private Boolean Gender;

  @Column(name = "Verification_code")
  private String verificationCode;

  @Column(name = "Active")
  private Boolean isActive;

  @Column(name = "Reset_password_token")
  private String resetPasswordToken;

//  @JsonIgnore
//  @OneToMany(mappedBy = "account")
//  List<Order> orders;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "Account_Role",
      joinColumns = @JoinColumn(name = "Account_Id"),
      inverseJoinColumns = @JoinColumn(name = "Role_Id"))
  private Set<Role> role = new HashSet<>();


  public Account(String username, String email, String fullName, Boolean gender, Boolean active, String encode) {
  }



  @JsonIgnore
  @OneToMany(mappedBy = "account")
  List<Rate> rates;

}
