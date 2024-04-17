package com.codingbox.querydsl;

import java.util.ArrayList;
import java.util.List;


import com.codingbox.querydsl.domain.Member;
import com.codingbox.querydsl.domain.QMember;
//import com.codingbox.querydsl.domain.QMember;
import com.codingbox.querydsl.domain.Team;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import static com.codingbox.querydsl.domain.QMember.*;
import static com.codingbox.querydsl.domain.QTeam.*;
public class QueryDSLMain12 {

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
			Member member3 = new Member("member3", 30, teamA);
			Member member4 = new Member("member4", 40, teamB);
			Member member5 = new Member(null, 100, teamB);
			Member member6 = new Member("member6", 100, teamB);
			Member member7 = new Member("member7", 100, teamB);
			em.persist(member1);
			em.persist(member2);
			em.persist(member3);
			em.persist(member4);
			em.persist(member5);
			em.persist(member6);
			em.persist(member7);

			// 초기화
			em.flush();
			em.clear();

		 
			// Case문
			List<String> result
				= queryFactory.select(
								member.age.when(10).then("열살")
										  .when(20).then("스무살")
										  .otherwise("기타")
									 )
							.from(member)
							.fetch();
			
			// 복잡한 조건
			List<String> result2
				= queryFactory.select( new CaseBuilder()
							.when(member.age.between(0, 20)).then("0~20살 사이")
							.when(member.age.between(21, 30)).then("21~30살 사이")
							.otherwise("기타")
							)
							  .from(member)
							  .fetch();
			
			for(String s : result2) {
				System.out.println("s : " + s);
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
