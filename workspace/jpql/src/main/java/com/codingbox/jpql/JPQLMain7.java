package com.codingbox.jpql;

import java.util.List;

import com.codingbox.jpql.domain.Member;
import com.codingbox.jpql.domain.Team;
import com.codingbox.jpql.dto.MemberDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JPQLMain7 {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try { 
			Member member = new Member();
			member.setUsername("member");
			member.setAge(1);
//			member.changeTeam(team);
			em.persist(member);
		
			em.flush();
			em.clear();
 
			// 서브쿼리
			String jpql = "select m from Member m where m.age > (select avg(m2.age) from Member m2)";
			List<Member> resultList
				= em.createQuery(jpql, Member.class)
					.getResultList();
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
			
		}
	}

}
