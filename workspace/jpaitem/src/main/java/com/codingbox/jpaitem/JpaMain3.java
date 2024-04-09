package com.codingbox.jpaitem;



//import com.codingbox.jpaitem.relation.Member;
//import com.codingbox.jpaitem.relation.Team;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain3 {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

	

		
		try { 
			// 양방향 매핑시 가장 많이 하는 실수 (중요!!!!!!!!!!!!!!!!!!!!!!!!!!!!)
			// TEAM 은 owner 가 아니기 때문에 Member의 데이터 값을 바꾸는 권한이 없음
//			Member member = new Member();
//			member.setName("member111111");
//			em.persist(member);
//			
//			Team team = new Team();
//			team.setName("TEAM1111111");
//			team.getMember().add(member);
//			em.persist(team);

			// 수정
			// Owner(여기서는 Member)를 가장 마지막에 작성해야함 ( 멤버가 데이터를 수정하는 권한이 있기 때문 )
//			Team team = new Team();
//			team.setName("GOODTEAM123");
//			em.persist(team);
			
//			Member member = new Member();
//			member.setName("gooodMember123");
//			member.changeTeam(team);
//			em.persist(member);
		
			// 양방향 매핑시에는 양쪽에 값을 모두 입력해 주어야 한다.
			// DB를 다시 다녀오지 않고, 객체 상태로만 넣어준다.
			// 해당 로직은 setter에서 정의함
//			team.getMember().add(member);
		
			
//			em.flush();
//			em.clear();
			
//			System.out.println("==========================================");
//			Team findTeam = em.find(Team.class, team.getId());
//			List<Member> members = findTeam.getMember();
//			
//			for(Member m : members) {
//				System.out.println("m = " + m.getName());
//			}
//			
//			System.out.println("==========================================");
			
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();

		}
	}

}
