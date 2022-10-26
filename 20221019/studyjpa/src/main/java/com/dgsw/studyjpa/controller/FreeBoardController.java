package com.dgsw.studyjpa.controller;


import com.dgsw.studyjpa.entity.FreeBoard;
import com.dgsw.studyjpa.repository.FreeBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.SchemaOutputResolver;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("board")
@RestController
public class FreeBoardController {

    @Autowired
    FreeBoardRepository freeBoardRepository;

    @GetMapping("list")
    public List<FreeBoard> list(){
        List<FreeBoard> list = freeBoardRepository.findAll();
        return list;
    }
    @PostMapping("write")
    public String write(String writer,String content){
        System.out.println("writer = "+writer);
        System.out.println("content = "+ content);
        FreeBoard fb = new FreeBoard();
        fb.setContent(content);
        fb.setWriter(writer);
        fb.setWdate(LocalDateTime.now());
        freeBoardRepository.save(fb);
        return "write";
    }
    @PostMapping("update")
    public String update(){
        return "update";
    }
    @PostMapping("delete")
    public String delete(){
        return "delete";
    }

}
