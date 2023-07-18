package com.apper.theblogservice;

import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.payload.BloggerDetails;
import com.apper.theblogservice.payload.CreateBlogRequest;
import com.apper.theblogservice.payload.CreateBloggerRequest;
import com.apper.theblogservice.payload.CreateBloggerResponse;
import com.apper.theblogservice.service.BloggerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/blogger")
public class BloggerApi {

    private final BloggerService bloggerService;

    public BloggerApi(BloggerService bloggerService) {
        this.bloggerService = bloggerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBloggerResponse createBlogger(@RequestBody @Valid CreateBloggerRequest request) {
        Blogger createdBlogger = bloggerService.createBlogger(request.getEmail(), request.getName(), request.getPassword());

        CreateBloggerResponse response = new CreateBloggerResponse();
        response.setId(createdBlogger.getId());
        response.setDateRegistration(createdBlogger.getCreatedAt());

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogger(@PathVariable String id) {
        Blogger blogger = bloggerService.getBlogger(id);
        if (blogger != null) {
            BloggerDetails bloggerDetails = new BloggerDetails();
            bloggerDetails.setId(blogger.getId());
            bloggerDetails.setName(blogger.getName());
            bloggerDetails.setEmail(blogger.getEmail());
            bloggerDetails.setDateRegistration(blogger.getCreatedAt());

            return ResponseEntity.ok(bloggerDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<BloggerDetails>> getAllBloggers() {
        List<Blogger> bloggers = bloggerService.getAllBloggers();

        List<BloggerDetails> bloggerDetailsList = new ArrayList<>();
        for (Blogger blogger : bloggers) {
            BloggerDetails bloggerDetails = new BloggerDetails();
            bloggerDetails.setId(blogger.getId());
            bloggerDetails.setName(blogger.getName());
            bloggerDetails.setEmail(blogger.getEmail());
            bloggerDetails.setDateRegistration(blogger.getCreatedAt());
            bloggerDetailsList.add(bloggerDetails);
        }

        return ResponseEntity.ok(bloggerDetailsList);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createBlog(@RequestBody @Valid CreateBlogRequest request) {
        Blogger blogger = bloggerService.getBlogger(request.getBloggerId());
        if (blogger != null) {
            Blog blog = new Blog();
            blog.setTitle(request.getTitle());
            blog.setBody(request.getBody());
            blog.setBlogger(blogger);

            Blog createdBlog = bloggerService.createBlog(blog);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdBlog);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blogger not found");
        }
    }

    @PutMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> updateBlog(@PathVariable String blogId, @RequestBody @Valid Blog updatedBlog) {
        Blog blog = bloggerService.updateBlog(blogId, updatedBlog.getTitle(), updatedBlog.getBody());
        if (blog != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBlog(@PathVariable String id) {
        Blog blog = bloggerService.getBlog(id);
        if (blog != null) {
            return ResponseEntity.ok(blog);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = bloggerService.getAllBlogs();
        if (!blogs.isEmpty()) {
            return ResponseEntity.ok(blogs);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/blogger/{bloggerId}")
    public ResponseEntity<?> getAllBlogsByBlogger(@PathVariable String bloggerId) {
        List<Blog> blogs = bloggerService.getAllBlogsByBlogger(bloggerId);
        if (!blogs.isEmpty()) {
            return ResponseEntity.ok(blogs);
        } else {
            return ResponseEntity.noContent().build();
        }
    }




}
