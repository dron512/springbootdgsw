package com.aaa.bbb.controller;

import com.aaa.bbb.components.ACompo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    ACompo aCompo;

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @GetMapping("")
    public String index(){
        aCompo.doA();
        return "main";
    }

    @GetMapping("freeboard")
    public String freeboard(Model model){
        List list = sqlSessionTemplate.selectList("test.findall");

//        sqlSessionTemplate.insert("test.insert");
//        sqlSessionTemplate.insert("test.insert")
        System.out.println(list);
        model.addAttribute("list",list);
        model.addAttribute("aaa","a값");
        return "freeboard";
    }
}
