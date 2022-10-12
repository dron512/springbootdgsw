package com.dgsw.mybatis.controller;

import com.dgsw.mybatis.dto.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    // select 해서 테이블 내용 뿌려주는거
    @GetMapping("findall")
    public String findall(Model model){
        System.out.println("findall");

        List<Test> testlist = sqlSessionTemplate.selectList("test.findall");

        model.addAttribute("a","10");
        model.addAttribute("testlist", testlist);

        return "findall";
    }

    @PostMapping("delete")
    public String delete(@RequestParam(required = false) int [] idx){
        System.out.println("delete");

        System.out.println(idx);
        List<Integer> list = new ArrayList<>();
        if (idx != null){
            for (int ele: idx)
               list.add(ele);
            System.out.println(list);
            sqlSessionTemplate.selectList("test.delete",list);
        }

        return "redirect:/board/findall";
    }
    //insert 해서 테이블에 행 삽입
    @GetMapping("insert")
    public String insert(Test test){
        return "insert";
    }

    @PostMapping("insert")
    public String pinsert(@Valid Test test, BindingResult bindingResult, Model model){

        if( bindingResult.hasErrors()){
            System.out.println("에러 있음");
            return "insert";
        }

        System.out.println(test);
        sqlSessionTemplate.insert("test.inserttest", test);

        return "redirect:/board/findall";
    }
}
