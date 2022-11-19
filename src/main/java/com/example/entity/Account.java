package com.example.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Accounts")
public class Account implements Serializable{
	@Id
	@Column(name="Username")
	private String Username;
	
	@Column(name="Password")
	private String Password;
	
	@Column(name="Email")
	private String Email;
	
	@Column(name="Fullname")
	private String Fullname;
	
	@Column(name="Gender")
	private Boolean Gender;
	
	
	@Column(name="Active")
	private Boolean Active;
	
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    List<Order> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    List<Authority> authorities;
	
    @JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Rate> accounts;
	
	
}
