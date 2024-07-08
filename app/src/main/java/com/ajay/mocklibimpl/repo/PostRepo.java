package com.ajay.mocklibimpl.repo;

import com.ajay.mocklibimpl.dao.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Post post) {

        System.out.println("For Data Insertion");
        String sql = "INSERT INTO posts (name, contents) VALUES (?, ?)";
        int i = jdbcTemplate.update(sql, post.getName(), post.getContents());
        System.out.println("Updation: " + i);
    }
}
