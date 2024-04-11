package com.codingbox.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingbox.shop.domain.Item;
import com.codingbox.shop.dto.ItemForm;
import com.codingbox.shop.repository.ItemRepository;
import com.codingbox.shop.service.ItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

	private final ItemService itemService;
	
	@GetMapping("/new")
	public String createForm(Model model) {
		model.addAttribute("form",new ItemForm());
		return "items/createItemForm";
	}
	
	// 상품저장
	// db insert
	// 성공시 home
	@PostMapping("/new")
	public String create(@Valid ItemForm form, BindingResult result) {
		if(result.hasErrors()) {
			return "items/createItemForm";
		}
		
		Item item = itemService.mappedBy(form);
		itemService.save(item);
		return "redirect:/";
	}

	@GetMapping("")
	public String getItems(Model model) {
		model.addAttribute("items", itemService.findItems());
		return "items/itemList";
	}
	
	@GetMapping("/{itemId}/edit")
	public String updateItemForm(@PathVariable long itemId, Model model) {
		model.addAttribute("item", itemService.findOne(itemId));
		return "items/updateItemForm";
	}


	
	
	
//	@PostMapping("/{itemId}/edit")
//	public String updateItem2(@PathVariable long itemId, Item item) {
//		Item newItem = new Item();
//		item.setId(form.getId());
//		item.setName(form.getName());
//		item.setPrice(form.getPrice());
//		item.setStockQuantity(form.getStockQuantity());
//		itemService.updateItem(form.getId(),item);
	
//		return "redirect:/items";
//	}

	@PostMapping("/{itemId}/edit")
	public String updateItem(@PathVariable long itemId, Item newItem) {
		
//		itemService.update(itemId, newItem.getName(), newItem.getPrice(), newItem.getStockQuantity()); 이런식으로, 파라미터를 줄줄이 써서 보내기도 함(변경사항 표시하기위해서)
		itemService.update(itemId, newItem);
		return "redirect:/items";
	}
	// 수정
	// 성공시 : /items
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
