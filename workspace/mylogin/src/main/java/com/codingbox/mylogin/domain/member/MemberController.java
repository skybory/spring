package com.codingbox.mylogin.domain.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
	private final MemberRepository memberRepository;
	
	
	/*
	 * @ModelAttribute("member") Member member
	 * -> model.addAttribute("member", new Member()); 를 대신함.
	 */
	
	@GetMapping("/add")
	public String addForm(@ModelAttribute("member") Member member) {
//		model.addAttribute("member", new Member());
		return "members/addMemberForm";
	}
	
//	@PostMapping("/add")
//	public String add(@RequestBody String entity) {
//		return entity;
//	}
	
}
