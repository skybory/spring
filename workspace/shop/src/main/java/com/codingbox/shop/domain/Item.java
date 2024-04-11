package com.codingbox.shop.domain;

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

@Entity
@Getter @Setter
@SequenceGenerator(
		name = "ITEM_SEQ_Generator",
		sequenceName="ITEM,Seq",
		initialValue = 1, allocationSize=1
		)
@NoArgsConstructor
public class Item {

	@Id @GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "ITEM_SEQ_Generator"
			)	
	@Column(name = "item_id")
	private Long id;
	
	private String name;
	private int price;
	private int stockQuantity;
	
}
