package com.ajay.mocklibimpl.repo;

import com.ajay.mocklibimpl.dao.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
}
