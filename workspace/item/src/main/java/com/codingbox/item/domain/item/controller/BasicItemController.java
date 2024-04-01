package com.codingbox.item.domain.item.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingbox.item.domain.item.Item;
import com.codingbox.item.domain.item.repository.ItemRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
	private final ItemRepository itemRepository;

//	@Autowired
	// 이렇게 생성자가 1개만 잇으면 @AutoWired 가 생략 가능

	// lombok으로 생략한 생성자
//	public BasicItemController(ItemRepository itemRepository) {
//		this.itemRepository=itemRepository;
//	}

	// test용 데이터를 추가
	@PostConstruct
	public void init() {
		itemRepository.save(new Item("testA", 10000, 10));
		itemRepository.save(new Item("testB", 20000, 20));
	}

	@PostConstruct
	public void init2() {
//		System.out.println("초기화 메서드");
	}

	@PostConstruct
	public void destroy() {
//		System.out.println("종료 메서드 호출");
	}

	@GetMapping
	public String items(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "basic/items";
	}

	@GetMapping("/{itemId}")
	public String item(@PathVariable long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/item";
	}

	@GetMapping("/add")
	public String add() {
		return "basic/addForm";
	}

//	@PostMapping("/add")
	public String saveV1(@RequestParam String itemName, @RequestParam int price, @RequestParam Integer quantity,
			Model model) {

		Item item = new Item();
		item.setItemName(itemName);
		item.setPrice(price);
		item.setQuantity(quantity);
		itemRepository.save(item);
		model.addAttribute("item", item);

		return "basic/item";
	}

//	@PostMapping("/add")
	public String saveV2(@ModelAttribute("item") Item item) {
//		@ModelAttribute가 해주는 역할
//		Item item = new Item();
//		item.setItemName(itemName);
//		item.setPrice(price);
//		item.setQuantity(quantity);
		itemRepository.save(item);
//		model.addAttribute("item", item);
		return "basic/item";
	}

	/*
	 * key 값을 날렸는데 어떻게 불러오는가?
	 * @ModelAttribute name(key값) 생략 가능. 
	 * model.addAttribute(item); 자동 추가, 생략 가능
	 * 생략시 model에 저장되는 name은 클래스명 첫 글자만 소문자로 등록.
	 * 즉, Item -> item 으로 등록됨
	 */
	
	// key값이 생략되어서 코드 가독성이 떨어짐
//	@PostMapping("/add")
	public String saveV3(@ModelAttribute Item item) {
		itemRepository.save(item);
		return "basic/item";
	}
	
//	마치 이런 상황과 같아짐. HelloData -> helloData
//	@PostMapping("/add")
//	public String saveV4(@ModelAttribute HelloData item) {
//		itemRepository.save(item);
//		model.addAttribute("helloData", item);
//		return "basic/item";
//	}
	
	// key값이 생략되어서 코드 가독성이 떨어짐
	
	/*
	 * @ModelAttribute 자체도 생략 가능
	 * model.addAttribute("item",item); 자동 추가됨 
	 * 가독성을 위해서 적당히 줄이는 것을 권장..!
	 */
	
//	@PostMapping("/add")
	public String saveV4(Item item) {
		itemRepository.save(item);
		return "basic/item";
	}
	
	
	@PostMapping("/add")
	public String saveV5(Item item) {
		itemRepository.save(item);
		return "redirect:/basic/items/" + item.getId();
	}
	
	/*
	 * @GetMapping, 
	 * url : http://localhost:9090/basic/items/3/edit
	 * 화면 : return basic/editForm.html
	 * -------------------------------------------------------
	 * 
	 */
			
	@GetMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item",item);
		return "basic/editForm";
	}
	
	/*
	 * postmapping 요청, /basic/items/3/edit 
	 * update (레파지토리 확인)
	 * 페이지 이동 : /basic/items/3 (상세페이지로 이동)
	 * 
	 */
	
	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
		itemRepository.update(itemId,item);
		return "redirect:/basic/items/{itemId}";
	}
}
