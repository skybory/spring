package com.codingbox.mylogin;

import org.springframework.stereotype.Component;

import com.codingbox.mylogin.domain.item.Item;
import com.codingbox.mylogin.domain.item.ItemRepository;
import com.codingbox.mylogin.domain.member.Member;
import com.codingbox.mylogin.domain.member.MemberRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component					// @Component 를 통해 scan의 대상으로 여김. (bean으로 등록함) = 싱글톤으로 등록
@RequiredArgsConstructor	// 생성자 주입
public class TestDataInit {
// postConstruct 로 안만들고, TestDataInit 으로 만들어보기
// 여기에 위치한다는 것은 component scan의 대상이 된다는 의미. 
	
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;
	
	// init() 안에 ItemA, 10000, 10 / itemB, 20000, 20 데이터 입력 (이름,가격,갯수)
	
	
	@PostConstruct
	public void init() {
		Member member = new Member();
		member.setLoginId("test");
		member.setPassword("test");
		member.setName("테스트");
		memberRepository.save(member);
		
		itemRepository.save(new Item("itemA", 10000, 10));
		itemRepository.save(new Item("itemB", 20000, 20));
		
	}
}