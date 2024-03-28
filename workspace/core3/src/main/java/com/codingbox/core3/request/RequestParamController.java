package com.codingbox.core3.request;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codingbox.core3.web.dto.HelloData;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class RequestParamController {

	// 반환 타입이 없으면서 응답값을 직접 입력하면, view를 조회하지 않는다.
	// void 타입이면서 response에 값을 쓰면, 쓴 값이 화면에 보이게 된다.
	@RequestMapping("/request-param-v1")
	public void requestparamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));
		System.out.println("username : " + username);
		System.out.println("age : " + age);
		response.getWriter().write("OK");
	}

	
	/*
	 * @ResopnseBody
	 *	- view 조회를 무시하고, HTTP message body에 직접 해당 내용을 입력함
	 *	-----------------------------------------------------------
	 * @RequestParam
	 * 	- 파라미터 이름으로 바인딩
	 */
	
	
	@ResponseBody
	@RequestMapping("/request-param-v2")
	public String requestparamV2(@RequestParam("username") String memberName, @RequestParam("age") int memberAge) {
		
		System.out.println("username : " + memberName);
		System.out.println("age : " + memberAge); 
		return "OK";
	}

	
	/*
	 * @RequestParam 사용시
	 * HTTP 파라미터 이름이 변수 이름과 같으면
	 * @RequestParam(name="...") 생략 가능
	 * 
	 */
	
	@ResponseBody
	@RequestMapping("/request-param-v3")
	public String requestparamV3(@RequestParam String username, @RequestParam int age) {
		
		System.out.println("username : " + username);
		System.out.println("age : " + age); 
		return "OK";
	}

	
	/*
	 * 
	 * @RequestParam 생략 가능
	 * 	-> String, int 등과 같은 기본형이면 생략 가능 (DTO 등과 같이 내가 지정한것들은 생략 불가능) 
	 * ----------------------------------------------------------------------------
	 * 	-> 권장하지 않음.
	 * 	-> 웬만하면 @RequestParam 을 입력해주자... 명확하게 요청 파라미터에서 데이터를 읽어온다는 것을 알 수 있음.
	 */
	
	@ResponseBody
	@RequestMapping("/request-param-v4")
	public String requestparamV4(String username, int age) {
		
		System.out.println("username : " + username);
		System.out.println("age : " + age); 
		return "OK";
	}
	
	
	/*
	 * @RequestParam.required 옵션
	 *   - 기본값이 true임
	 * /request-param-required					-> username 이 없으므로 예외 발생
	 * /request-param-required?username			->	빈 문자로 통과
	 * /request-param-required					-> int age 주의. null 값 대체 때문에 Integer 사용, 또는 defaultValue 사용
	 * /request-param-required?username=김자바	->
	 * 
	 */
	
	@ResponseBody
	@RequestMapping("/request-param-required")
	public String requestParamRequired(@RequestParam(required = true) String username, @RequestParam(required = false) Integer age) {
		
		System.out.println("username : " + username);
		System.out.println("age : " + age); 
		return "OK";
	}
	
	
	/*
	 * @RequestParam
	 *   - defaultValue 옵션 사용시
	 *   	-> 기본값을 세팅
	 *   	-> 빈 문자열인 경우에도 적용
	 *   	-> 이미 기본값이 세팅이 되었기 때문에 required 옵션이 의미가 없음
	 * 
	 */
	@ResponseBody
	@RequestMapping("/request-param-default")
	public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username, @RequestParam(required = false, defaultValue = "-1") int age) {
		
		System.out.println("username : " + username);
		System.out.println("age : " + age); 
		return "OK";
	}
	

	@ResponseBody
	@RequestMapping("/request-param-map")
	public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
		System.out.println("username : " + paramMap.get("username"));
		System.out.println("age : " + paramMap.get("age")); 
		return "OK";
	}
	
	
	@ResponseBody
	@RequestMapping("/model-attribute-v1")
	public String modelAttributeV1(@RequestParam String username, @RequestParam int age) {
		HelloData helloData = new HelloData();
		helloData.setUsername(username);
		helloData.setAge(age);
		
		System.out.println("username : " + helloData.getUsername());
		System.out.println("age : " + helloData.getAge());

		return "OK";
	}
	
	
	/*
	 * @ModelAttribute 사용
	 * 
	 */
	
	@ResponseBody
	@RequestMapping("/model-attribute-v2")
	public String modelAttributeV2(@ModelAttribute HelloData helloData) {
		
		System.out.println("username : " + helloData.getUsername());
		System.out.println("age : " + helloData.getAge());
		System.out.println("helloData : " + helloData.toString());
		return "OK";
	}
	
	
	/*
	 * @ModelAttribute, @RequestParam 생략 가능
	 *  - 혼란이 발생할 수 있다
	 *  - String, int, Integer, ... 단순 타입이면	-> RequestParam 이 생략되었다고 판단
	 *  - DTO 객체가 파라미터에 있으면 					-> @ModelAttribute 가 생략되었다고 판단
	 * 
	 */
	
	@ResponseBody
	@RequestMapping("/model-attribute-v3")
	public String modelAttributeV3(HelloData helloData) {
		
		System.out.println("username : " + helloData.getUsername());
		System.out.println("age : " + helloData.getAge());
		System.out.println("helloData : " + helloData.toString());
		return "OK";
	}
	
}















