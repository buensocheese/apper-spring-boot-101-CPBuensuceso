package com.apper.theblogservice.service;

import com.apper.theblogservice.exception.BlogNotFoundException;
import com.apper.theblogservice.exception.BloggerNotFoundException;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.repository.BlogRepository;
import com.apper.theblogservice.repository.BloggerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BloggerService {

    private final BloggerRepository bloggerRepository;
    private final BlogRepository blogRepository;

    public BloggerService(BloggerRepository bloggerRepository, BlogRepository blogRepository) {
        this.bloggerRepository = bloggerRepository;
        this.blogRepository = blogRepository;
    }

    // Create a new blogger
    public Blogger createBlogger(String email, String name, String password) {
        Blogger blogger = new Blogger();
        blogger.setEmail(email);
        blogger.setName(name);
        blogger.setPassword(password);
        return bloggerRepository.save(blogger);
    }

    // Check if blogger ID exists
    public boolean isIdExisting(String id) {
        return bloggerRepository.existsById(id);
    }

    // Get blogger by ID
    public Blogger getBlogger(String id) {
        Optional<Blogger> bloggerResult = bloggerRepository.findById(id);
        return bloggerResult.get();
    }

    // Check if email is already used by another blogger
    public boolean isEmailAlreadyUsed(String email) {
        return bloggerRepository.existsByEmail(email);
    }

    // Get all bloggers
    public List<Blogger> getAllBlogger() {
        return (List<Blogger>) bloggerRepository.findAll();
    }

    // Create a new blog for a blogger
    public Blog createBlog(String title, String body, String bloggerId) {
        Blogger blogger = getBlogger(bloggerId);

        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setBody(body);
        blog.setDateCreated(LocalDateTime.now());
        blog.setLastUpdated(LocalDateTime.now());
        blog.setBlogger(blogger);
        return blogRepository.save(blog);
    }

    // Get blog by ID
    public Blog getBlog(String blogId) throws BlogNotFoundException {
        Optional<Blog> blogResult = blogRepository.findById(blogId);
        if(blogResult.isEmpty()){
            throw new BlogNotFoundException("Blog not found with Id: " + blogId);
        }
        return blogResult.get();
    }

    // Update a blog's title and body
    public Blog updateBlog(String blogId, String title, String body) throws BlogNotFoundException{
        Blog blog = getBlog(blogId);
        blog.setTitle(title);
        blog.setBody(body);
        return blogRepository.save(blog);
    }

    // Get all blogs
    public List<Blog> getAllBlog() {
        return (List<Blog>) blogRepository.findAll();
    }

    // Get blogs by blogger ID
    public List<Blog> getBlogsByBlogger(String bloggerId) throws BloggerNotFoundException {
        if (!bloggerRepository.existsById(bloggerId)) {
            throw new BloggerNotFoundException("Blogger ID Not Found");
        }
        Optional<Blogger> bloggerResult = bloggerRepository.findById(bloggerId);
        if (bloggerResult.isPresent()) {
            Blogger blogger = bloggerResult.get();
            return blogger.getBlogs();
        }
        return new ArrayList<>();
    }
}
