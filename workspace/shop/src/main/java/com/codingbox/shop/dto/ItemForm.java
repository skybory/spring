package com.codingbox.shop.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemForm {

//	private Long id;
	@NotEmpty(message = "아이템 이름은 필수값입니다.")
	private String name;
	private int price;
	private int stockQuantity;
	
}
