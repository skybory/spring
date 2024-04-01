package com.codingbox.item.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {
	
	private Long id;
	private String itemName;
	private Integer price;	// 가격이 null 일 수 있음
	private Integer quantity;	// 수량이 null 일 수 있음.
	
	
	public Item(String itemName, Integer price, Integer quantity) {
		super();
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
	
	public Item() {
		
	}
}

