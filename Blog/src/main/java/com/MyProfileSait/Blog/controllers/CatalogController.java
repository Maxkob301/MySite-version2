package com.MyProfileSait.Blog.controllers;

import com.MyProfileSait.Blog.Models.Post;
import com.MyProfileSait.Blog.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class CatalogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/catalog")
    public String CatalogMain( Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "Catalog";
    }

    @GetMapping("/catalog/add")
    public String AddPostMain( Model model) {
        return "Post-add";
    }

    @PostMapping("/catalog/add")
    public String productPostAdd( @RequestParam String title,@RequestParam String anons, @RequestParam String full_text, Model model){
        Post post = new Post(title,anons,full_text);
        postRepository.save(post);
        return "Post-add";
    }
    @GetMapping("/about")
    public String AboutMain( Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "About";
    }
    @GetMapping("/about/{id}")
    public String BlogDetails( @PathVariable(value = "id") long id, Model model) {
        if(!postRepository.existsById(id)){
          return "redirect:/catalog";
        }

       Optional<Post> post = postRepository.findById(id);
       ArrayList<Post> result = new ArrayList<>();
       post.ifPresent(result :: add);
       model.addAttribute("post",result);
        return "Post-details";
    }
    @GetMapping("/catalog/{id}/edit")
    public String BlogEdit( @PathVariable(value = "id") long id, Model model) {
        if(!postRepository.existsById(id)){
            return "redirect:/catalog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> result = new ArrayList<>();
        post.ifPresent(result :: add);
        model.addAttribute("post",result);
        return "Post-edit";
    }
    @PostMapping("/catalog/{id}/edit")
    public String productPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title,@RequestParam String anons, @RequestParam String full_text, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/";
    }
    @PostMapping("/catalog/{id}/remove")
    public String productPostDelete(@PathVariable(value = "id") long id, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/about";
    }

}
