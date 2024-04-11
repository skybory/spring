package com.codingbox.shop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingbox.shop.domain.Item;
import com.codingbox.shop.domain.Member;
import com.codingbox.shop.service.ItemService;
import com.codingbox.shop.service.MemberService;
import com.codingbox.shop.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

	private final MemberService memberService;
	private final ItemService itemService;
	private final OrderService orderService;
	
	@GetMapping
	public String createForm(Model model) {
		List<Member> members = memberService.findMembers();
		List<Item> items = itemService.findItems();
		model.addAttribute("members",members);
		model.addAttribute("items", items);
		return "order/orderForm";
	}
	
	@PostMapping
	public String order(long memberId, Item item) {
		return "redirect:/";
	}
}
