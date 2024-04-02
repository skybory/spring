package com.codingbox.mylogin.filter;

import java.io.IOException;

import org.springframework.util.PatternMatchUtils;

import com.codingbox.mylogin.session.SessionConst;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {

	private static final String[] whitelist = { "/", "/members/add", "/login"
			, "/logout", "/css/*" };

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		System.out.println("인증 체크 필터 시작 : " + requestURI);

		if (isLoginCheckPath(requestURI)) {
			System.out.println("인증 체크 로직 실행 : " + requestURI);
			;
			HttpSession session = httpRequest.getSession(false);
			
			if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
				System.out.println("미인증 사용자 요청 : " + requestURI);
				httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
			// 미인증 사용자는 다음으로 진행하지 않고 끝
			}

		}
		// 그대로 진행
		chain.doFilter(request, response);
	}
	
	// 화이트 리스트의 경우 인증 체크 하지 않는다
	private boolean isLoginCheckPath(String requestURI) {
		return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
	}

}
