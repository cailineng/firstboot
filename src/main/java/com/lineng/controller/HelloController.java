package com.lineng.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lineng.mapper.PlusdemoMapper;
import com.lineng.model.Cat;
import com.lineng.model.Plusdemo;
import com.lineng.service.PlusdemoService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.baomidou.mybatisplus.core.toolkit.Wrappers.lambdaQuery;

@Controller
public class HelloController {
	@Autowired
	private AmqpTemplate rabbitTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;
	@Resource
    private PlusdemoMapper plusdemoMapper;
	@Resource
	private PlusdemoService plusdemoService;

	@RequestMapping("/helloneng")
	public ModelAndView helloneng(Map<String, Object> map) {
		map.put("hello", "from TemplateController.helloHtml");
		return new ModelAndView("/view/testTemplate");
	}




	@RequestMapping("/neng")
	public String index(ModelMap map) {
		for(int i=0;i<30;i++) {
			//String context = "hello :" +i;
			Cat cat = new Cat();
			cat.setCatAge("19");
			cat.setCatName("cailineng"+i);
			cat.setId(20);
			this.rabbitTemplate.convertAndSend("hello", cat);
		}
		//System.out.println("蔡力能哈哈");
		Cat cat = new Cat();
		cat.setId(1);
		cat.setCatAge("18");
		cat.setCatName("蔡力能");
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

	@RequestMapping("/testQueue")
	@ResponseBody
	public Map testQueue() {
		Cat cat = new Cat();
		cat.setId(1);
		cat.setCatName("wenwen");
		cat.setCatAge("25");
		this.rabbitTemplate.convertAndSend("hello",cat);
		Map rmap = new HashMap();
		rmap.put("haha","testQueue");
		return rmap;
	}

	@RequestMapping("/testRabbitTopic")
	@ResponseBody
	public Map testRabbitTopic() {
		String context = "hi, i am message delete";
		this.rabbitTemplate.convertAndSend("linengDemoExchange", "topic.shop.delete", context);
		Map rmap = new HashMap();
		rmap.put("haha","xixi");
		return rmap;
	}

	@RequestMapping("/testRabbitTopic2")
	@ResponseBody
	public Map testRabbitTopic2() {
		String context = "hi, i am messages insert";
		this.rabbitTemplate.convertAndSend("linengDemoExchange", "topic.shop.insert", context);
		Map rmap = new HashMap();
		rmap.put("haha","xixi2");
		return rmap;
	}

	@RequestMapping("/testRabbitTopic3")
	@ResponseBody
	public Map testRabbitTopic3() {
		String context = "hi, i am messages update";
		this.rabbitTemplate.convertAndSend("linengDemoExchange", "topic.shop.update", context);
		Map rmap = new HashMap();
		rmap.put("haha","xixi3");
		return rmap;
	}


	@RequestMapping("/testPlusDemo")
	@ResponseBody
	public List<Plusdemo> testPlusDemo() {
		List<Plusdemo> list = plusdemoMapper.selectList(null);
		return list;
	}

	@RequestMapping("/testPlusDemo2")
	@ResponseBody
	public List<Plusdemo> testPlusDemo2() {
		List<Plusdemo> list = plusdemoMapper.selectList(Wrappers.<Plusdemo>lambdaQuery().ge(Plusdemo::getAge,20));
		return list;
	}


	@RequestMapping("/testPlusDemo3")
	@ResponseBody
	public List<Plusdemo> testPlusDemo3(Plusdemo plusdemo) {
        //Wrappers<Plusdemo> queryWrapper = new
        QueryWrapper<Plusdemo> queryWrapper = new QueryWrapper();
        queryWrapper.gt(ObjectUtils.isNotEmpty(plusdemo.getAge()),"age",plusdemo.getAge());
        queryWrapper.gt(ObjectUtils.isNotEmpty(plusdemo.getId()),"id",plusdemo.getId());
		List<Plusdemo> list = plusdemoService.list(queryWrapper);
		return list;
	}

    @RequestMapping("/testPlusDemo4")
    @ResponseBody
    public List<Plusdemo> testPlusDemo4(Plusdemo plusdemo) {
        List<Plusdemo> list = plusdemoService.list(Wrappers.<Plusdemo>lambdaQuery()
                                                    .gt(ObjectUtils.isNotEmpty(plusdemo.getAge()),Plusdemo::getAge,plusdemo.getAge())
                                                    .gt(ObjectUtils.isNotEmpty(plusdemo.getId()),Plusdemo::getId,plusdemo.getId())
                                                    );
        return list;
    }
	 //  return giftCategoryDao.list(Wrappers.lambdaQuery(giftCategory).orderByAsc(GiftCategory::getListorder));

/*    @ResponseBody
    @RequestMapping("/saveCat")
    public Map<String,Object> saveCat(Cat cat) {
        mongoTemplate.save(cat);
        Map<String,Object> map = new HashMap<>(8);
        map.put("test","test");
        return map;
    }*/
}