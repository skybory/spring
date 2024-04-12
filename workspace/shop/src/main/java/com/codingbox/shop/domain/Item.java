package com.codingbox.shop.domain;

import com.codingbox.shop.exception.NotEnoughStockException;

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
	
	//============================= 비즈니스 로직 ==============================
	// stock 감소
	
	public void removeStock(int count) {
		int restStock = this.stockQuantity - count;
		
		// 재고 부족시 로직 처리
		if (restStock < 0) {
			// 사용자 정의 exception
			throw new NotEnoughStockException("재고가 부족합니다");
		}
		this.stockQuantity = restStock;
	}
	
	// stock 증가
	
	public void addStock(int count) {
		this.stockQuantity += count;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
