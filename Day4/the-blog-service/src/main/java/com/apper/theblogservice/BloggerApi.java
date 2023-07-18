package com.apper.theblogservice;

import com.apper.theblogservice.exception.BlogNotFoundException;
import com.apper.theblogservice.exception.BloggerListException;
import com.apper.theblogservice.exception.BloggerNotFoundException;
import com.apper.theblogservice.exception.ExistingEmailException;
import com.apper.theblogservice.payload.*;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.service.BloggerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BloggerApi {

    private final BloggerService bloggerService;

    public BloggerApi(BloggerService bloggerService) {
        this.bloggerService = bloggerService;
    }

    @PostMapping("/blogger")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBloggerResponse createBlogger(@RequestBody @Valid CreateBloggerRequest request) throws ExistingEmailException {
        System.out.println(request);

        if (bloggerService.isEmailAlreadyUsed(request.getEmail())) {
            throw new ExistingEmailException("Email is already registered");
        } else {
            Blogger createdBlogger = bloggerService.createBlogger(request.getEmail(), request.getName(), request.getPassword());
            CreateBloggerResponse response = new CreateBloggerResponse();
            response.setId(createdBlogger.getId());
            response.setDateRegistration(createdBlogger.getCreatedAt());

            return response;
        }
    }

    @GetMapping("/blogger/{id}")
    public BloggerDetails getBlogger(@PathVariable @Valid String id) throws BloggerNotFoundException {
        if (bloggerService.isIdExisting(id)) {
            Blogger blogger = bloggerService.getBlogger(id);
            BloggerDetails bloggerDetails = new BloggerDetails();
            bloggerDetails.setId(blogger.getId());
            bloggerDetails.setName(blogger.getName());
            bloggerDetails.setEmail(blogger.getEmail());
            bloggerDetails.setDateRegistration(blogger.getCreatedAt());
            return bloggerDetails;
        } else {
            throw new BloggerNotFoundException("ID is invalid");
        }
    }

    @GetMapping("/blogger")
    public List<BloggerDetails> allBloggers() throws BloggerListException {
        List<BloggerDetails> responseList = new ArrayList<>();
        for (Blogger blogger : bloggerService.getAllBlogger()) {
            BloggerDetails response = getAllBloggers(blogger);
            responseList.add(response);
        }
        if (responseList.isEmpty()) {
            throw new BloggerListException("No bloggers registered");
        } else {
            return responseList;
        }
    }

    private BloggerDetails getAllBloggers(Blogger blogger) {
        BloggerDetails response = new BloggerDetails();
        response.setId(blogger.getId());
        response.setName(blogger.getName());
        response.setEmail(blogger.getEmail());
        response.setDateRegistration(blogger.getCreatedAt());
        return response;
    }

    @PostMapping("/blog")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBlogResponse createBlog(@RequestBody @Valid CreateBlogRequest request) throws BloggerNotFoundException {
        if (bloggerService.isIdExisting(request.getBloggerId())) {
            Blog createdBlog = bloggerService.createBlog(request.getTitle(), request.getBody(), request.getBloggerId());
            CreateBlogResponse response = new CreateBlogResponse();
            response.setId(createdBlog.getId());
            response.setBloggerId(createdBlog.getBlogger().getId());
            response.setDateCreated(createdBlog.getDateCreated());
            response.setLastUpdated(createdBlog.getDateCreated());
            return response;
        } else{
            throw new BloggerNotFoundException("No registered blogger found with blogger id");
        }
    }

    @PutMapping("/blog/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CreateBlogResponse updateBlog(@PathVariable String blogId, @RequestBody @Valid UpdateBlogRequest request) throws BlogNotFoundException {
        Blog updateBlog = bloggerService.updateBlog(blogId, request.getTitle(), request.getBody());
        CreateBlogResponse response = new CreateBlogResponse();
        response.setId(updateBlog.getId());
        response.setBloggerId(updateBlog.getBlogger().getId());
        response.setDateCreated(updateBlog.getDateCreated());
        response.setLastUpdated(updateBlog.getLastUpdated());

        return response;
    }

    @GetMapping("/blog/{blogId}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public BlogDetails getBlog(@PathVariable String blogId) throws BlogNotFoundException {

        Blog blog = bloggerService.getBlog(blogId);
        BlogDetails blogDetails = new BlogDetails();
        blogDetails.setBloggerId(blog.getBlogger().getId());
        blogDetails.setTitle(blog.getTitle());
        blogDetails.setBody(blog.getBody());
        blogDetails.setCreatedDate(blog.getDateCreated());
        blogDetails.setUpdatedDate(blog.getLastUpdated());
        return blogDetails;
    }

    @GetMapping("/blog")
    public List<BlogDetails> allBlogs() throws BlogNotFoundException {
        List<BlogDetails> responseList = new ArrayList<>();
        for (Blog blog : bloggerService.getAllBlog()) {
            BlogDetails response = getAllBlogs(blog);
            responseList.add(response);
        }
        if (responseList.isEmpty()) {
            throw new BlogNotFoundException("No blogs registered");
        } else {
            return responseList;
        }
    }

    private BlogDetails getAllBlogs(Blog blog) {
        BlogDetails response = new BlogDetails();
        response.setBloggerId(blog.getBlogger().getId());
        response.setTitle(blog.getTitle());
        response.setBody(blog.getBody());
        response.setCreatedDate(blog.getDateCreated());
        response.setUpdatedDate(blog.getLastUpdated());
        return response;
    }

    @GetMapping("/blog/blogger/{bloggerId}")
    public List<BlogDetails> getBlogsByBlogger(@PathVariable String bloggerId) throws BloggerNotFoundException{
        List<BlogDetails> responseList = new ArrayList<>();
        for (Blog blog : bloggerService.getBlogsByBlogger(bloggerId)) {
            BlogDetails response = blogsByBloggerResponse(blog);
            responseList.add(response);
        }
        if (responseList.isEmpty()) {
            throw new BloggerNotFoundException("No blogs registered");
        } else {
            return responseList;
        }
    }

    private BlogDetails blogsByBloggerResponse(Blog blog) {
        BlogDetails response = new BlogDetails();
        response.setBloggerId(blog.getId());
        response.setTitle(blog.getTitle());
        response.setBody(blog.getBody());
        response.setCreatedDate(blog.getDateCreated());
        response.setUpdatedDate(blog.getLastUpdated());
        return response;
    }

}
