package com.codingbox.test2;

import java.time.LocalDateTime;


import com.codingbox.test2.domain.Member_notEmbedded;
import com.codingbox.test2.domain.Order_notEmbedded;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain_notEmbedded {

	public static void main(String[] args) {

		
		// 1-2 번 문제 제출입니다. (embedded 되어있지 않은 entity)
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {

			Member_notEmbedded member = new Member_notEmbedded();
			member.setName("김자바");
			member.setCity("서울");
			member.setStreet("역삼역은 거꾸로 해도 역삼역");
			member.setZipcode("123");
			
			em.persist(member);
			System.out.println("member 정보 : " + member.toString());

			
			for (int i = 0; i < 10; i++) {
				Order_notEmbedded order = new Order_notEmbedded();
				order.setOrderDate(LocalDateTime.now());
				order.setStatus("접수" + i);
				order.changeMember(member);
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
