package com.codingbox.core3.web.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingbox.core3.web.data.User;


@Controller
@RequestMapping("basic")
public class BasicController {

	
	@GetMapping("/text-basic")
	public String textBasinc(Model model) {
	
		model.addAttribute ("data", "HelloSpring");
		return "basic/text-basic";
	}
	
	@GetMapping("/text-unescaped")
	public String textUnescaped(Model model) {
		
		model.addAttribute ("data", "<b>HelloSpring</b>");
		return "basic/text-unescaped";
	}

	@GetMapping("/variable")
	public String variable(Model model) {
		User userA = new User("userA", 10);
		User userB = new User("userB", 10);
		
		List<User> list = new ArrayList<>();
		list.add(userA);
		list.add(userB);
		
		Map<String, User> map = new HashMap<>();
		map.put("userA", userA);
		map.put("userB", userB);
		
		model.addAttribute("user",userA);
		model.addAttribute("users",list);
		model.addAttribute("userMap",map);
		return "basic/variable";
	}
	
	@GetMapping("link")
	public String link(Model model) {
		model.addAttribute("param1", "data1");
		model.addAttribute("param2", "data2");
		return "basic/link";
	}
	
	
}