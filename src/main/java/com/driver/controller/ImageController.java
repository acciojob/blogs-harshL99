package com.driver.controller;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    ImageService service;

    @PostMapping("/createImage")
    public ResponseEntity<Image> createAndReturn(@RequestBody Blog blog,
                                                 @RequestParam String description,
                                                 @RequestParam String dimensions) {
        Image image = null;
        image=service.createAndReturn(blog,description,dimensions);
        return new ResponseEntity<>(image, HttpStatus.CREATED);
    }

    @GetMapping("/countImagesInScreen/{id}/{screenDimensions}")
    public ResponseEntity<Integer> countImagesInScreen(@PathVariable int id, @PathVariable String screenDimensions){
        int count = 0;
        //Need to implement confusing...How to calculate dimensions, size...
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @DeleteMapping("/deleteImage")
    public ResponseEntity<Void> deleteImage(@RequestBody Image image) {
        service.deleteImage(image);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



