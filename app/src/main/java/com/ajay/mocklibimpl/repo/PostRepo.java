package com.ajay.mocklibimpl.repo;

import com.ajay.mocklibimpl.dao.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class PostRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Post save(Post post) {
//        System.out.println("For Data Insertion");
//        String sql = "INSERT INTO posts (name, contents) VALUES (?, ?)";
//        int i = jdbcTemplate.update(sql, post.getName(), post.getContents());
//        System.out.println("Updation: " + i);

        System.out.println("For Data Insertion");

        String sql = "INSERT INTO posts (name, contents) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, post.getName());
            ps.setString(2, post.getContents());
            return ps;
        }, keyHolder);

        //Long generatedId = keyHolder.getKey().longValue();
        post.setId(post.getId());

        System.out.println("Updation: " + post.getId());

        return post;
    }
}
