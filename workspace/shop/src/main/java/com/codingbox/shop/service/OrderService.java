package com.codingbox.shop.service;

import org.springframework.stereotype.Service;

import com.codingbox.shop.domain.Item;
import com.codingbox.shop.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	
	public void order(long memberId, Item item) {
		
	}
}
