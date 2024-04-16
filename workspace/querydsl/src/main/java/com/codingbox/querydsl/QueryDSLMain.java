package com.codingbox.querydsl;

import java.util.List;
import com.codingbox.querydsl.domain.Member;
//import com.codingbox.querydsl.domain.QMember;
import com.codingbox.querydsl.domain.Team;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import static com.codingbox.querydsl.domain.QMember.*;
public class QueryDSLMain {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		// queryDSL
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		tx.begin();

		try {  
			Team teamA = new Team("teamA");
			Team teamB = new Team("teamB");
			em.persist(teamA);
			em.persist(teamB);
			
			Member member1 = new Member("member1", 10, teamA);
			Member member2 = new Member("member2", 20, teamA);
			Member member3 = new Member("member3", 30, teamB);
			Member member4 = new Member("member4", 40, teamB);
			Member member5 = new Member("member5", 50, teamB);
			em.persist(member1);
			em.persist(member2);
			em.persist(member3);
			em.persist(member4);
			em.persist(member5);
			
			
			// 초기화
			em.flush();
			em.clear();
 

//			List<Member> fetch
//				= queryFactory.selectFrom(member)
////							  .from(member)		// selectFrom 을 쓰면 select (member) from (member) 를 줄여줌.
//							  .fetch();
//			
//			Member findMember1
//				= queryFactory.selectFrom(member)
//							  .fetchOne();
			
			Long totalCount = 
					queryFactory.select(member.count())
								.from(member)
								.fetchOne();
			
			System.out.println("totalCount : " + totalCount);
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
			
		}
	}

}
