package com.example.SRK.Backend.Controller;

import com.example.SRK.Backend.Model.Blog;
import com.example.SRK.Backend.Repository.BlogRepository;
import com.example.SRK.Backend.Service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/blogs")
@CrossOrigin(origins = "https://darkcyan-woodcock-495538.hostingersite.com/")
public class BlogController {

    private final BlogRepository blogRepository;

    public BlogController(BlogRepository blogRepository, BlogService blogService) {
        this.blogRepository = blogRepository;
        this.blogService = blogService;
    }

    private final BlogService blogService;



    @GetMapping
    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }

    @GetMapping("/slugs")
    public List<String> getBlogSlugs() {
        return blogRepository.findAll()
                .stream()
                .map(Blog::getSlug)  // use the new slug field
                .collect(Collectors.toList());
    }

    @GetMapping("/{slug}")
    public Blog getBlogBySlug(@PathVariable String slug) {
        return blogRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
    }




    @GetMapping("/{id}")
    public Blog getBlogById(@PathVariable Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    // Add new blog
    @PostMapping
    public Blog createBlog(@RequestBody Blog blog) {
        return blogService.saveBlog(blog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id){
        if (blogRepository.existsById(id)){
            blogRepository.deleteById(id);
            return ResponseEntity.ok("Blog Deleted Successfully");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Blog not found");
        }
    }

    @PutMapping("/{id}")
    public Blog updateBlog(@PathVariable long id, @RequestBody Blog blogDetails){
        return blogService.updateBlog(id, blogDetails);
    }

}
