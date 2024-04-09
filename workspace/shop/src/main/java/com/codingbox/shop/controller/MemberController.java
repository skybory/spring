package com.codingbox.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingbox.shop.dto.MemberForm;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/members")
public class MemberController {

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
		return "redirect/";
	}
}
