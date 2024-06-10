package com.ajay.hypertest.repo;

import com.ajay.hypertest.dao.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
}
