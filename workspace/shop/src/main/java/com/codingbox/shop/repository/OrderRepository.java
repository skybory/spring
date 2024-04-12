package com.codingbox.shop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import com.codingbox.shop.domain.Order;
import com.codingbox.shop.domain.OrderStatus;
import com.codingbox.shop.domain.QMember;
import com.codingbox.shop.domain.QOrder;
import com.codingbox.shop.dto.OrderSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

	private final EntityManager em;
	public void save(Order order) {
		em.persist(order);
	}

	public Order findOne(Long orderId) {
		return em.find(Order.class, orderId);
	}

	// dependency 추가해서 사용
	// 검색, 전체 조회, QueryDSL - 실무에서 많이 사용 (JPA가 못하는것도 해줌-거의없음)
	public List<Order> findAll(OrderSearch orderSearch) {

		JPAQueryFactory query = new JPAQueryFactory(em);
		QOrder order = QOrder.order;
		QMember member = QMember.member;
		
		return query.select(order)
					.from(order)
					.join(order.member, member)		// 두 번째 파라미터 member -> 알리아스
//					.where(order.status.eq(orderSearch.getOrderStatus()),
//					member.name.like(orderSearch.getMemberName()))
					.where(statusEq(orderSearch.getOrderStatus()), nameLike(orderSearch.getMemberName()))
					.fetch();
	
	}				
	
	private BooleanExpression statusEq(OrderStatus orderStatus) {
		if( orderStatus == null) {
			return null;
		}
		return QOrder.order.status.eq(orderStatus);
	}

	private BooleanExpression nameLike(String memberName) {
		if(memberName == null) {
			return null;
		}
		return QMember.member.name.contains(memberName);
	}
}

















