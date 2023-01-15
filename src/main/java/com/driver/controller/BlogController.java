package com.driver.controller;

import com.driver.models.*;
import com.driver.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService service;

    @GetMapping("/showBlogs")
    public ResponseEntity<Integer> getAllBlogs() {
        int countOfBlogs = service.showBlogs().size();
        return new ResponseEntity<>(countOfBlogs, HttpStatus.OK);
    }

    @PostMapping("/createBlog")
    public ResponseEntity createBlog(@RequestParam Integer userId ,
                                           @RequestParam String title,
                                           @RequestParam String content) {
        service.createAndReturnBlog(userId,title,content);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/add-image/{blogId}")
    public ResponseEntity<String> addImage(@PathVariable int blogId, @RequestParam String description, @RequestParam String dimensions) {

            service.addImage(blogId,description,dimensions);
            return new ResponseEntity<>("Added image successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteBlog/{blogId}")
    public ResponseEntity<Void> deleteBlog(@PathVariable int blogId) {
        service.deleteBlog(blogId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}




