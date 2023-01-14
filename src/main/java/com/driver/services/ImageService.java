package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    @Autowired
    BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        List<Image> imageList=blog.getImageList();
        Image newImage=new Image(description,dimensions);
        imageList.add(newImage);
        blogRepository.save(blog);
        return newImage;
    }

    public void deleteImage(int id){
         imageRepository2.deleteById(id);
    }

    public Image findById(int id) {
         return imageRepository2.findById(id).orElse(null);
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions
        return 0;
        //In case the image is null, return 0

    }
}
