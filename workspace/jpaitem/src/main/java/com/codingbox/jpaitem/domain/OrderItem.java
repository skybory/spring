package com.codingbox.jpaitem.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(
		name = "ORDERITEM_SEQ_Generator",
		sequenceName="ORDERITEM,Seq",
		initialValue = 1, allocationSize=1
		)
public class OrderItem {
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "ORDERITEM_SEQ_Generator"
			)
	@Column(name="ORDER_ITEM_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	private Order order;
	
	
	@ManyToOne
	@JoinColumn(name = "ITEM_ID")
	private Item item;
	
	private int orderPrice;
	private int count;	
}
