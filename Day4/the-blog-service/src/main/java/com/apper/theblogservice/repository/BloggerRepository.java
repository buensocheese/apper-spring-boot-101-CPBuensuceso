package com.apper.theblogservice.repository;

import com.apper.theblogservice.model.Blogger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggerRepository extends CrudRepository<Blogger, String> { // string is the data type of the unique key of db
    boolean existsByEmail(String email);

}