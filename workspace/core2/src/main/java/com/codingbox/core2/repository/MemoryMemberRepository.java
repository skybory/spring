package com.codingbox.core2.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.codingbox.core2.dto.Member;

//@Repository	주석처리를 함으로써 spring 의 스캔 범위에서 벗어남
public class MemoryMemberRepository implements MemberRepository{

	
	// 메모리를 사용
	private static Map<Integer, Member> store = new HashMap<>();
	private static int sequence = 0;
	
	
	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}

}
