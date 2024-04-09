package com.codingbox.jpaitem;



import java.time.LocalDateTime;

import com.codingbox.jpaitem.embedded.Address;
import com.codingbox.jpaitem.embedded.Member;
import com.codingbox.jpaitem.embedded.Period;

//import com.codingbox.jpaitem.relation.Member;
//import com.codingbox.jpaitem.relation.Team;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain4 {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try { 
			Member member = new Member();
			member.setUsername("user");
			member.setAdress(new Address("서울", "강남", "5252"));
			member.setPeriod(new Period(LocalDateTime.now(),LocalDateTime.now()));

			em.persist(member);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();

		}
	}

}
