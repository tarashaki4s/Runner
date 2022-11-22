package com.example.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Products")
public class Product implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	 Integer Id;
	
	@Column(name="Name")
	private String Name;
	
	@Column(name="Price")
	private double Price;
	
	@Column(name="Size")
	private String Size;
	
	@Column(name="Image1")
	private String Image1;
	@Column(name="Image2")
	private String Image2;
	
	@Column(name="Amount")
	private Integer Amount;
	
	@Column(name="Color")
	private String Color;
	
	@Column(name="Active")
	private boolean Active;
	
	@Column(name="Quantitysold")
	private Integer Quantitysold;
	
	@Column(name="Describe")
	private String Describe;
	
	@ManyToOne
    @JoinColumn(name = "Categoryid")
    Category category;
	
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    List<OrderDetail> orderDetails;
    
    @JsonIgnore
	@OneToMany(mappedBy = "product")
	List<Rate> rates;
}
