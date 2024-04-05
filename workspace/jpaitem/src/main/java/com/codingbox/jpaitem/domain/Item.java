 package com.codingbox.jpaitem.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Getter @Setter @NoArgsConstructor
public class Item {

	@Id @GeneratedValue
	@Column(name = "ITEM_ID")
	private Long id;
	private String name;
	private int price;
	private int stockQuantity;

}
