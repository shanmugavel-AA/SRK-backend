package com.example.SRK.Backend.Service;

import com.example.SRK.Backend.Model.Blog;
import com.example.SRK.Backend.Repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Transactional
    public Blog saveBlog(Blog blog) {
        if (blog.isFeatured()){
            blogRepository.unsetAllFeatured();
        }
        return blogRepository.save(blog);
    }

    @Transactional
    public Blog updateBlog(long id, Blog blogDetails) {

        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));

        if (blogDetails.isFeatured()) {
            blogRepository.unsetAllFeatured();
        }

        blog.setTitle(blogDetails.getTitle());
        blog.setDescription(blogDetails.getDescription());
        blog.setCategory(blogDetails.getCategory());
        blog.setImgUrl(blogDetails.getImgUrl());
        blog.setBannerUrl(blogDetails.getBannerUrl());
        blog.setContentHtml(blogDetails.getContentHtml());
        blog.setDate(blogDetails.getDate());
        blog.setFeatured(blogDetails.isFeatured());
        return blogRepository.save(blog);
    }
}
