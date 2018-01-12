package com.lineng.controller;

import com.lineng.model.Cat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {
	@RequestMapping("/helloneng")
	public ModelAndView helloneng(Map<String, Object> map) {
		map.put("hello", "from TemplateController.helloHtml");
		return new ModelAndView("/view/testTemplate");
	}


	@RequestMapping("/neng")
	public String index(ModelMap map) {
		Cat cat = new Cat();
		cat.setId(1);
		cat.setCatAge("18");
		cat.setCatName("wenwen");
		// 加入一个属性，用来在模板中读取
		map.addAttribute("host", "lineng.com");
		map.addAttribute("cat", cat);

		List<Cat> list = new ArrayList<Cat>();
		list.add(cat);

		Cat cat2 = new Cat();
		cat2.setId(12);
		cat2.setCatAge("182");
		cat2.setCatName("wenwen2");
		list.add(cat2);
		map.addAttribute("catList", list);
		// return模板文件的名称，对应src/main/resources/templates/index.html
		map.addAttribute("testId", 5);
		map.addAttribute("hehe", 4);
		return "indexneng";
	}


	@RequestMapping("/testThe")
	public String testThe(ModelMap map, Integer id, Integer hehe) {
		System.out.println(hehe);
		System.out.println(id);
		map.addAttribute("id", id);
		return "testThe";
	}


	//spring security测试
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}

	@RequestMapping("/helloIndex")
	public String helloIndex() {
		return "helloIndex";
	}

	@RequestMapping("/loginneng")
	public String loginneng() {
		return "loginneng";
	}


	//spring security测试
	@RequestMapping("/403")
	public String error() {
		return "myerror";
	}
}