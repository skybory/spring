package com.codingbox.jpql;

import java.util.List;

import com.codingbox.jpql.domain.Member;
import com.codingbox.jpql.dto.MemberDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JPQLMain4 {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try { 
			Member member = new Member();
			member.setUsername("member1");
			member.setAge(10);
			em.persist(member);
			
			em.flush();
			em.clear();
			
			List<MemberDTO> result =
			em.createQuery("select new com.codingbox.jpql.dto.MemberDTO(m.username, m.age) from Member m")
				.getResultList();
			
			MemberDTO memberDTO = result.get(0);
			System.out.println("memberDTO : " + memberDTO.getUsername());
			System.out.println("memberDTO : " + memberDTO.getAge());
			
			 
//			List resultList =
//			em.createQuery("select m.username, m.age from Member m")
//				.getResultList();
//			
//			Object o = resultList.get(0);
//			Object[] result = (Object[]) o;
//			System.out.println("username = " + result[0]);
//			System.out.println("age = " + result[1]);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
			
		}
	}

}
