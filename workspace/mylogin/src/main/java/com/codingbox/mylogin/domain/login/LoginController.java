package com.codingbox.mylogin.domain.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingbox.mylogin.domain.member.Member;
import com.codingbox.mylogin.session.SessionConst;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService loginService;

//	url : login
//	return : login/loginForm
//	thymeleaf 적용
	
	@GetMapping("/login")
	public String loginForm(@ModelAttribute("loginForm")LoginForm loginForm) {
		return "login/loginForm";
	}

	
	/*
	 * Model : forward 방식 
	 * RedirectAttributes : redirect 방식으로 전달
	 */
	
//	@PostMapping("/login")
	public String loginV2(@ModelAttribute LoginForm form,Model model, 
			RedirectAttributes redirectAttributes, HttpServletResponse response) {
		
		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
		if(loginMember == null) {
			// 로그인 실패
			model.addAttribute("msg", "로그인실패");
			return "login/loginForm";
		}
		// 로그인 성공
		// 쿠키에 로그인 정보 저장
		
		else {
			Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
			response.addCookie(idCookie);
			redirectAttributes.addFlashAttribute("msg", "로그인성공");
			return "redirect:/";
		}
		
	}
	
//	@PostMapping("/login")
	public String loginV3(@ModelAttribute LoginForm form, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
		if(loginMember == null) {
			// 로그인 실패
			model.addAttribute("msg", "로그인실패");
			return "login/loginForm";
		}

		// 로그인 성공 처리
		// 세션이 있으면 세션 반환, 없으면 신규 세션 생성
		HttpSession session = request.getSession();
		// 세션에 로그인 회원 정보를 보관
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
		return "redirect:/";
	}
	

	@PostMapping("/login")
	public String loginV4(@ModelAttribute LoginForm form, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request, @RequestParam(defaultValue = "/")String redirectURL) {
		
		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
		if(loginMember == null) {
			// 로그인 실패
			model.addAttribute("msg", "로그인실패");
			return "login/loginForm";
		}
		
		// 로그인 성공 처리
		// 세션이 있으면 세션 반환, 없으면 신규 세션 생성
		HttpSession session = request.getSession();
		// 세션에 로그인 회원 정보를 보관
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
		System.out.println("redirectURL================== " + redirectURL);
		
		return "redirect:"+redirectURL;
	}
	
	
	
	
	
	
	
	
	
	// setMaxAge(0) : 쿠키 즉시 종료
//	@PostMapping("/logout")
	public String logoutV2(HttpServletResponse response) {
		Cookie cookie = new Cookie("memberId", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "redirect:/";
	}
	
	
	
	
	@PostMapping("/logout")
	public String logoutV3(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
	
}
