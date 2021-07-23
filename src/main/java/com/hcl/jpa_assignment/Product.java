package com.hcl.jpa_assignment;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long product_id;

	@Column(unique = true, nullable = false)
	private double price;

	@Basic
	private String name;

	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

}
