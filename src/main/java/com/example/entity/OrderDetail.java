package com.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="OrderDetails")
public class OrderDetail implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	@Column(name="Quantity")
	private Integer Quantity;
	
	@ManyToOne
	@JoinColumn(name="OrderId")
	private Order order;
	@ManyToOne
	@JoinColumn(name="ProductId")
	private Product product;
}
