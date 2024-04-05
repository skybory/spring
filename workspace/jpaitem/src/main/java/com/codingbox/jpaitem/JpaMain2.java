package com.codingbox.jpaitem;


import java.util.List;

import com.codingbox.jpaitem.relation.Member;
import com.codingbox.jpaitem.relation.Team;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain2 {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

	

		
		try {
			Team team = null;
			Member member = null;
			for(int i = 0; i<10; i++) {
				team = new Team();
				team.setName("Team" + i);
				// 영속 상태가 되면 PK의 값이 세팅이 된 후 영속상태가 된다.
				em.persist(team);

				member = new Member();
				member.setName("member" + i);
				member.setTeam(team);
				em.persist(member);
				
			}
//System.out.println("team.getName() : " + team.getName()); : Team9
	

			// 강제로 DB 쿼리를 보고싶을 때
			em.flush();
			em.clear();
			
			// 조회
			Member findMember = em.find(Member.class, 1L);
			Team findTeam = findMember.getTeam();
			System.out.println("findTeamMember : " + findTeam.getName());

			// 수정
			Team newTeam = em.find(Team.class, 5L);
			findMember.setTeam(newTeam);
			System.out.println("updateFindTeamName : " + newTeam.getName());
			System.out.println("updateTeam.getId : " + newTeam.getId());
			
			
			// 양방향 매핑
			Member findSideMember = em.find(Member.class, 5L);
			List<Member> members = findSideMember.getTeam().getMember();
			for(Member m : members) {
				System.out.println("result = " + m.getName());
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
