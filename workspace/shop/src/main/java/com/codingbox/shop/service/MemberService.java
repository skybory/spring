package com.codingbox.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingbox.shop.domain.Member;
import com.codingbox.shop.repository.MemberRepository;

import lombok.RequiredArgsConstructor;


/*
 * @Transactional
 *   - DB와 관련된, 트랜잭션이 필요한 서비스 클래스 혹은 메서드에
 *   @Transactional 추가
 *   - 일련의 작업들을 묶어서 하나의 단위로 처리할때 사용.
 *   - spring 이 제공해주는 것을 권장
 *   - 옵션 : readOnly = true/false
 * 			-> 읽기 전용일때 사용
 * 			-> 비용을 아끼게 된다
 */


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)		// 클래스 단위로 readOnly = true 를 건다면 (optional) , 멤버 서비스에 들어오는 모든 요청은 리드온리가 되고, 조인은 리드온리가 아니기 때문에
// 아닌 애들만 선택적으로 readonly를 풀어줘야한다. 그러면 @Transactional 을 선언해주면 된다. readOnly에 대한 메서드가 많기 때문에, 아닌 것들을 풀어주는 처리를 해주는게 보편적이다.
public class MemberService {

	private final MemberRepository memberRepository;

	// Create
	@Transactional
	public Long join(Member member) {
		memberRepository.save(member);
		return member.getId();
	}
	
	// Select
//	@Transactional(readOnly = true)		// select 에 사용
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	
	
	
	
}


