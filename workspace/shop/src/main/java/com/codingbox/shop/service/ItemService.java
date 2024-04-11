package com.codingbox.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingbox.shop.domain.Item;
import com.codingbox.shop.dto.ItemForm;
import com.codingbox.shop.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

	private final ItemRepository itemRepository;
	
	@Transactional
	public Long save(Item item) {
		itemRepository.save(item);
		return item.getId();
	}

	public List<Item> findItems() {
		return itemRepository.findAll();
	}
	
	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}
	
	public Item mappedBy(ItemForm form) {
		Item item = new Item();
		item.setName(form.getName());
		item.setPrice(form.getPrice());
		item.setStockQuantity(form.getStockQuantity());
		return item;
	}

	@Transactional
	public void update(long itemId, Item newItem) {
		
		// 변경을 원치않는 값의 경우 주석으로 처리 
		// 필요한 값만 update 원한다면, 변경감지를 해줘야 함
		// 즉, merge는 실무에서 쓰지 않는다.
		
		/*
		 * 영속성 컨텍스트 영역에서는 save or merge를 호출할 필요가 없다
		 * 영속성 컨텍스트에 변화가 일어나게 되고, 변화가 일어나는 것을 jpa가 감지한다.
		 * -> 이게 변경감지에 의해서 데이터를 update하는 방법이다
		 */
		Item oldItem = itemRepository.findOne(itemId);
		oldItem.setName(newItem.getName());
		oldItem.setPrice(newItem.getPrice());
		oldItem.setStockQuantity(newItem.getStockQuantity());
//		save(newItem);		-> 영속성 컨텍스트에서 발생한 변화이기 때문에 save가 필요없음 (위에 설명있음)
	}

		// 이렇게도 사용한다. 개발자의 의도가 담긴 업데이트
	//	public void update(long itemId, String name, int price, int stockQuantity) {
//		Item oldItem = itemRepository.findOne(itemId);
//		oldItem.setName(name);
//		oldItem.setPrice(price);
//		oldItem.setStockQuantity(stockQuantity);
//	}
}
