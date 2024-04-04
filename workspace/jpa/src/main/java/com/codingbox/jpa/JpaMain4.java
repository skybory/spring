package com.codingbox.jpa;


import com.codingbox.jpa.entity.Member3;

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
			// Member3 이라는 테이블에
			// id : ID_A
			// name : HELLO
			// insert
			Member3 member = new Member3();		// 멤버3 형태의 member 객체 생성
			Member3 member2 = new Member3();		// 멤버3 형태의 member 객체 생성
//			member.setId("ID_A");				// id값 지정
			member.setUsername("HELLO");		// username값 지정 -> name 으로 칼럼 명 바뀜
			member2.setUsername("hi");
			em.persist(member);		// 영속
			em.persist(member2);		// 영속
			
			tx.commit();			// flush()
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();

		}
	}

}
