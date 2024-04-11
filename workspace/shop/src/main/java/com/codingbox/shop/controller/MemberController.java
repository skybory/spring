package com.codingbox.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingbox.shop.domain.Address;
import com.codingbox.shop.domain.Member;
import com.codingbox.shop.dto.MemberForm;
import com.codingbox.shop.service.MemberService;

import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@GetMapping("/new")
	public String createForm(Model model) {
		// validation 처리와 같은 내용 때문에 빈 값이라도 보내줌.
		model.addAttribute("memberForm", new MemberForm());
		return "members/createMemberForm";
	}
	
	// @Valid 다음에 Binding Result 있으면, error가 발생시 Binding에 담아준다.
	@PostMapping("/new")
	public String createClear(@Valid MemberForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return "members/createMemberForm";
		}

		Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
		Member member = new Member();
		member.setName(form.getName());
		member.setAddress(address);
		
		memberService.join(member);
		
		return "redirect:/";
	}
	
	// url : /members
	// select 한 결과값 담아주기 55분까지
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("members",memberService.findMembers());
		return "members/memberList";
	}
}
