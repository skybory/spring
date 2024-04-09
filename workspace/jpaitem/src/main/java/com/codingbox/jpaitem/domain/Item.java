 package com.codingbox.jpaitem.domain;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Getter @Setter @NoArgsConstructor
@SequenceGenerator(
		name = "ITEM_SEQ_Generator",
		sequenceName="ITEM,Seq",
		initialValue = 1, allocationSize=1
		)
public class Item {

	@Id @GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "ITEM_SEQ_Generator"
			)	
	@Column(name = "ITEM_ID")
	private Long id;
	private String name;
	private int price;
	private int stockQuantity;

	@OneToMany(mappedBy = "item")
	private List<OrderItem> orderItems = new ArrayList<>();
	
	public void addOrderItem(OrderItem orderItem) {
		orderItem.setItem(this);
		this.orderItems.add(orderItem);
	}
}
