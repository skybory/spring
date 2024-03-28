package com.codingbox.core2.repository;

import java.util.List;

import com.codingbox.core2.dto.Member;

public interface MemberRepository {

	
	// 회원 저장
	Member save(Member member);
	
	// 전체 찾기
	List<Member> findAll();
	
}
