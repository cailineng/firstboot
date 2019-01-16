package com.lineng.controller;

import com.lineng.model.Teacher;
import com.lineng.mongodao.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/firstMongo")
public class FirstMongoController {
    @Autowired
    TeacherRepository teacherRepository;
    @Resource
    private MongoTemplate mongoTemplate;
    @RequestMapping("/saveTeacher")
    @ResponseBody
    public Map<String,Object> saveTeacher(Teacher teacher) {
        mongoTemplate.save(teacher);
        Map<String,Object> map = new HashMap<>(8);
        map.put("test","test");
        return map;
    }


    @RequestMapping("/saveTeacher2")
    @ResponseBody
    public Map<String,Object> saveTeacher2(Teacher teacher) {
        teacherRepository.save(teacher);
        Map<String,Object> map = new HashMap<>(8);
        map.put("test2","test2");
        return map;
    }

    @RequestMapping("/getById")
    @ResponseBody
    public Teacher getById(String id) {
        Teacher t = teacherRepository.getById(id);
        return t;
    }

    @RequestMapping("/getListByUserName")
    @ResponseBody
    public List<Teacher> getListByUserName(String userName) {
        List<Teacher> teacherList = teacherRepository.getByUserName(userName);
        return teacherList;
    }

    @RequestMapping("/getUserNameById")
    @ResponseBody
    public Integer getUserNameById(String id) {
        Integer userName = teacherRepository.getAgeById(id);
        return userName;
    }


    @RequestMapping("/countByUserName")
    @ResponseBody
    public Integer countByUserName(String userName) {
        Integer count = teacherRepository.countByUserName(userName);
        return count;
    }

    @RequestMapping("/countAll")
    @ResponseBody
    public Long countAll() {
        Long count = teacherRepository.count();
        return count;
    }

    @RequestMapping("/getListWithSort")
    @ResponseBody
    public List<Teacher> getListWithSort() {
        Sort.Order idOrder = new Sort.Order(Sort.Direction.DESC, "age");
        Sort.Order nameOrder = new Sort.Order(Sort.Direction.ASC,"userName");

        Sort sort = new Sort(idOrder, nameOrder);
        List<Teacher> students = (List<Teacher>) this.teacherRepository.findAll(sort);
        return students;
    }


    @RequestMapping("/getPageList")
    @ResponseBody
    public Page<Teacher> getPageList() {
        Sort.Order idOrder = new Sort.Order(Sort.Direction.DESC, "age");
        Sort.Order nameOrder = new Sort.Order(Sort.Direction.ASC,"userName");

        Sort sort = new Sort(idOrder, nameOrder);

        Page<Teacher> teachersList = teacherRepository.findAll(new PageRequest(2, 2));
        return teachersList;
    }


}
