package com.codingbox.jpql;

import java.util.List;

import com.codingbox.jpql.domain.Member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class JPQLMain {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			// 1.param : JPQL
			// 2.param : 응답 클래스의 type
			// 타입 정보가 명확할 때
			TypedQuery<Member> query =
			em.createQuery("select m from Member m", Member.class);

			List<Member> resultList = query.getResultList();
			
			// 타입 정보가 String.class 로 반환 타입이 명확할 때
			TypedQuery<String> query2 =
					em.createQuery("select m.username from Member m", String.class);
			
			// String, int : 이렇게 반환타입이 명확하지 않을 때
			Query query3 = 
					em.createQuery("select m.username, m.age from Member m");
			
			TypedQuery<Member> query4 =
					em.createQuery("select m from Member m where m.id=10", Member.class);

			Member result = query4.getSingleResult();
			// 값이 있다고 보장될 때 만 사용
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
			
		}
	}

}
