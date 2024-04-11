package com.codingbox.shop.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codingbox.shop.domain.Item;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
	@Autowired
	private final EntityManager em;
	
	public void save(Item item) {
//		if(item.getId()== null) {
//			em.persist(item);
//		} else {
//			em.merge(item);
//		}
		em.persist(item);
	}

	public List<Item> findAll(){
		return em.createQuery("select i from Item i", Item.class).getResultList();
	}
	
	public Item findOne(Long itemId) {
		return em.find(Item.class, itemId);
	}
}
