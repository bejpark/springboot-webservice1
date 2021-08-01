package com.bejpark.springboot.web;

import com.bejpark.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts",postsService.findAllDesc()); //posts로 전달
        return "index"; //index.mustache로 이동
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";//posts-save.mustache로 이동
    }
}
