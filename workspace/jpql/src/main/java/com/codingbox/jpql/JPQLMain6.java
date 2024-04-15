package com.codingbox.jpql;

import java.util.List;

import com.codingbox.jpql.domain.Member;
import com.codingbox.jpql.domain.Team;
import com.codingbox.jpql.dto.MemberDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JPQLMain6 {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try { 
			Team team = new Team();
			team.setName("teamA");
			em.persist(team);
			
			Member member = new Member();
			member.setUsername("member");
			member.setAge(1);
			member.changeTeam(team);
			em.persist(member);
		
			em.flush();
			em.clear();
 
			// left outer join						// outer 생략 가능
			String jpql2 = "select t from Member m left outer join m.team t";
			List<Member> resultList2 = em.createQuery(jpql2, Member.class)
										.getResultList();
			
			
//			// inner 조인							//inner 생략 가능		//where 추가 가능
//			String jpql = "select m from Member m inner join m.team t where t.name = :teamName";
//			List<Member> resultList
//				= em.createQuery(jpql, Member.class)
//					.getResultList();
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
			
		}
	}

}
