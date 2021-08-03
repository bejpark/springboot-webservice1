package com.bejpark.springboot.web;

import com.bejpark.springboot.config.auth.LoginUser;
import com.bejpark.springboot.config.auth.dto.SessionUser;
import com.bejpark.springboot.service.posts.PostsService;
import com.bejpark.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts",postsService.findAllDesc()); //posts로 전달
        if(user!=null){
            model.addAttribute("userName",user.getName());
        }
        return "index"; //index.mustache로 이동
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";//posts-save.mustache로 이동
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }
}
