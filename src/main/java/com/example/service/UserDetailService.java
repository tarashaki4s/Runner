package com.example.service;

import com.example.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailService implements UserDetails {

  private Long id;

  private String userName;
  private String password;
  private String email;
  private String fullName;
  private Boolean gender;
  private Boolean isActive;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailService(Long id,String userName, String email, String password,String fullName,Boolean gender,Boolean isActive,
                         Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.userName = userName;
    this.email = email;
    this.password = password;
    this.fullName = fullName;
    this.gender = gender;
    this.isActive = isActive;
    this.authorities = authorities;
  }

  public static UserDetailService build(Account acc) {
    List<GrantedAuthority> authorities = acc.getRole().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());

    return new UserDetailService(
            acc.getId(),
            acc.getUsername(),
            acc.getEmail(),
            acc.getPassword(),
            acc.getFullName(),
            acc.getGender(),
            acc.getIsActive(),
            authorities);
  }
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }
  private Account account;

  public UserDetailService(Account account) {
    this.account = account;
  }


  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  public String getEmail() {
    return email;
  }

  public String getFullName() {
    return fullName;
  }

  public Boolean getGender() {
    return gender;
  }
  public Boolean getActive() {
    return isActive;
  }
  public Long getId() {
    return id;
  }


  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailService user = (UserDetailService) o;
    return Objects.equals(id, user.id);
  }

}
