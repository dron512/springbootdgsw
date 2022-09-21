package com.dgsw.mybatis.controller;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    // select 해서 테이블 내용 뿌려주는거
    @GetMapping("findall")
    public String findall(Model model){
        System.out.println("findall");

        System.out.println(sqlSessionTemplate.selectList("test.findall"));

        model.addAttribute("a","10");

        return "findall";
    }
    //insert 해서 테이블에 행 삽입
    @GetMapping("insert")
    public String insert(){
        return "insert";
    }
}
