package com.apper.theblogservice.service;

import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.repository.BlogRepository;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blog createBlog(String title, String body, Blogger blogger) {
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setBody(body);
        blog.setBlogger(blogger);

        return blogRepository.save(blog);
    }
}


