package com.apper.theblogservice.service;

import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.repository.BloggerRepository;
import com.apper.theblogservice.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BloggerService {

    private final BloggerRepository bloggerRepository;
    private final BlogRepository blogRepository;

    public BloggerService(BloggerRepository bloggerRepository, BlogRepository blogRepository) {
        this.bloggerRepository = bloggerRepository;
        this.blogRepository = blogRepository;
    }

    public Blogger createBlogger(String email, String name, String password) {
        Optional<Blogger> existingBlogger = bloggerRepository.findByEmail(email);
        if (existingBlogger.isPresent()) {
            throw new IllegalArgumentException("Email is already registered");
        }

        Blogger blogger = new Blogger();
        blogger.setEmail(email);
        blogger.setName(name);
        blogger.setPassword(password);

        return bloggerRepository.save(blogger);
    }

    public Blogger getBlogger(String id) {
        return bloggerRepository.findById(id).orElse(null);
    }

    public List<Blogger> getAllBloggers() {
        return (List<Blogger>) bloggerRepository.findAll();
    }

    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public Blog updateBlog(String blogId, String title, String body) {
        Blog blog = blogRepository.findById(blogId).orElse(null);
        if (blog != null) {
            blog.setTitle(title);
            blog.setBody(body);
            blog.setLastUpdated(LocalDateTime.now());

            return blogRepository.save(blog);
        } else {
            return null;
        }
    }

    public Blog getBlog(String blogId) {
        return blogRepository.findById(blogId).orElse(null);
    }

    public List<Blog> getAllBlogs() {
        return StreamSupport.stream(blogRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Blog> getAllBlogsByBlogger(String bloggerId) {
        return blogRepository.findByBloggerId(bloggerId);
    }


}

