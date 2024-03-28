package com.codingbox.core3.web.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

	@RequestMapping("/response-view-v1")
	public ModelAndView responseView1() {
		ModelAndView mav = new ModelAndView("response/hello").addObject("data", "hello!");
		return mav;
	}
	
	@RequestMapping("/response-view-v2")
	public String responseView2(Model model) {
		model.addAttribute("data","modeldata");
		
		return "response/hello";
	}

	
	/*
	 * @ResponseBody가 없으면, response/hello 로 뷰 리졸버가 실행되어 뷰를 찾고 랜더링한다.
	 * 그러나 @ResponseBody가 있으면, 뷰 리졸버를 실행하지 않고, HTTP 메시지 바디에 직접 response/hello 라는 문자가 입력된다.
	 * 
	 */
	
}
