package com.example.SRK.Backend.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    private String category;
    private String imgUrl;
    private String date;
    private boolean featured = false;
    private String bannerUrl;
    private String metaTitle;
    private String metaDescription;
    private String slug;


    @Lob
    @Column(columnDefinition = "TEXT")
    private String contentHtml;  // store all heading, para , images as HTML

    @Lob
    @Column(columnDefinition = "TEXT")
    private String faqJson; // Json String of FAQ

}
