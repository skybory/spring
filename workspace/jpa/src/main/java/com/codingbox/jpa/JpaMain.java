package com.codingbox.jpa;

import com.codingbox.jpa.entity.Member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Member member = new Member();

			Member findMember = em.find(Member.class, 1L);
//			System.out.println("findMember.id : " + findMember.getId());
//			System.out.println("findMember.name : " + findMember.getName());

			// 회원 수정
//			findMember.setName("HELLO");
//			em.persist(findMember);
			
			// 회원 삭제
			em.remove(findMember);
			
			
			// 회원 추가
//			member.setId(1L);
//			member.setName("UserA")
//			em.persist(member);
			
//			em.persist(findMember);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();

		}
	}

}
