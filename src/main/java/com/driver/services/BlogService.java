package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    UserRepository userRepository1;

    public List<Blog> showBlogs(){
        //find all blogs
       List<Blog> blogList=new ArrayList<>();
       blogList=blogRepository1.findAll();
       return blogList;
    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        Date currentTime=new Date();
        Blog newBlog=new Blog(title,content,currentTime);
        User user=userRepository1.findById(userId).orElse(null);
        if(user!=null){
            List<Blog> blogList=user.getBlogList();
            blogList.add(newBlog);
            userRepository1.save(user);
        }
        //updating the blog details
        Blog blog=blogRepository1.findBytitle(title);
        blog.setTitle(blog.getTitle());
        blog.setContent(blog.getContent());
        blog.setPubDate(new Date());
        blogRepository1.save(blog);

        //Updating the userInformation and changing its blogs
        User existingUser=userRepository1.findById(user.getId()).orElse(null);
        if(existingUser!=null){
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setBlogList(null);
            userRepository1.save(user);
        }
    }

    public Blog findBlogById(int blogId){
        //find a blog
        return blogRepository1.findById(blogId).orElse(null);
    }

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog after creating it
        Blog blog=blogRepository1.findById(blogId).orElse(null);
        if(blog!=null){
            List<Image> imageList=blog.getImageList();
            Image newImage=new Image(description,dimensions);
            imageList.add(newImage);
            blogRepository1.save(blog);
        }
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        blogRepository1.deleteById(blogId);
    }
}
