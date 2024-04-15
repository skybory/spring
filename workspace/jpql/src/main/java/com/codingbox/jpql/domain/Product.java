package com.codingbox.jpql.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

	@Id @GeneratedValue
	@Column(name="PRODUCT_ID")
	private long id;
	
	private String name;
	private int price;
	private int stockAmount;
}
