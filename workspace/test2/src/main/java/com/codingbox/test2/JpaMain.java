package com.codingbox.test2;

import java.time.LocalDateTime;


import com.codingbox.test2.domain.Address;
import com.codingbox.test2.domain.Member;
import com.codingbox.test2.domain.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {

		
		// 1-3 번 문제 제출입니다.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Member member = new Member();
			Address address = new Address("서울", "거꾸로 해도 역삼역", "123");
			member.setName("김자바");
			member.setAddress(address);
			System.out.println("member 정보 : " + member.toString());
			em.persist(member);
			
			
			for (int i = 0; i < 10; i++) {
				Order order = new Order();
				order.setOrderDate(LocalDateTime.now());
				order.setStatus("접수" + i);
				order.changeMember(member);
//				member.addOrder(order);
				em.persist(order);
				System.out.println("order 정보 : " + order.toString());
			}

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();

		}
	}

}
