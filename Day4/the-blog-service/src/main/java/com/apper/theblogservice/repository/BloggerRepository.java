package com.apper.theblogservice.repository;

import com.apper.theblogservice.model.Blogger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// findByEmail for checking registered email
@Repository
public interface BloggerRepository extends CrudRepository<Blogger, String> {
    Optional<Blogger> findByEmail(String email);
}