package com.apper.theblogservice.repository;

import com.apper.theblogservice.model.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface BlogRepository extends CrudRepository<Blog, String> {

    @Query("SELECT b FROM Blog b WHERE b.blogger.id = :bloggerId")
    List<Blog> findByBloggerId(@Param("bloggerId") String bloggerId);

}
