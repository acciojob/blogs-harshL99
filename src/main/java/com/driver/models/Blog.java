package com.driver.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Blog{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private Date pubDate;

    @OneToMany(targetEntity = Image.class,cascade = CascadeType.ALL)
    @JoinColumn(name="blogId",referencedColumnName = "id")
    private List<Image> imageList;
    public Blog(){

    }

    public Blog(String title, String content, Date pubDate,List<Image> imageList) {
        this.title = title;
        this.content = content;
        this.pubDate = pubDate;
        this.imageList=imageList;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){this.id=id;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
}