package com.codingbox.jpql;

import java.util.List;

import com.codingbox.jpql.domain.Member;
import com.codingbox.jpql.dto.MemberDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JPQLMain5 {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try { 

			for(int i=0; i<100; i++) {
			Member member = new Member();
			member.setUsername("member" + i);
			member.setAge(i);
			em.persist(member);
		
			}
			em.flush();
			em.clear();

			// 페이징 처리
			String jpql = "select m from Member m order by m.id desc";
			List<Member> resultList 
				= em.createQuery(jpql, Member.class)
					.setFirstResult(10)	// 10번째부터
					.setMaxResults(20)	// 20개 가져와, 예상 : 10~39 까지 나옴.
					.getResultList();
			
			System.out.println("result.size : " + resultList.size());
			
			for(Member member : resultList) {
				System.out.println("member = " + member.toString());
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
