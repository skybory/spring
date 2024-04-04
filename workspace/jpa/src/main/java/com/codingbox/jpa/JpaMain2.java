package com.codingbox.jpa;

import java.util.List;

import com.codingbox.jpa.entity.Member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain2 {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			// jpql
			// JPA는 쿼리를 짤 때 Table을 대상으로 쿼리를 짜지 않고
			// Member 객체를 통해서 쿼리를 짠다고 보면 된다.
//			List<Member> result = em.createQuery("select m from Member as m", Member.class)
//									.getResultList();
			
			List<Member> result = em.createQuery("select m from Member as m", Member.class)
					.setFirstResult(5)	// 5번부터
					.setMaxResults(10)	// 10개를 가져와
					.getResultList();	// 결과물 리스트로 출력
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();

		}
	}

}
