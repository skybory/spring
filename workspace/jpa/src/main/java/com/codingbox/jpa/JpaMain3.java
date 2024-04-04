package com.codingbox.jpa;

import java.util.List;

import com.codingbox.jpa.entity.Member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain3 {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try { 
			Member member = new Member();	// 비영속
			member.setId(100L);				// 비영속
			member.setName("HelloJPA");		// 비영속
			
			// 여기서부터 영속상태
			// EntityManager 안에 있는 영속성 컨텍스트에 의해
			// 관리가 된다는 뜻.
			System.out.println("=================before=================");
			em.persist(member);				// 영속
			System.out.println("=================after=================");			
			tx.commit();					// DB insert
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();

		}
	}

}
