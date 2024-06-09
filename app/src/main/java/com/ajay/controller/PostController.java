package com.ajay.controller;


import com.ajay.dao.Post;
import com.ajay.dto.RequestDTO;
import com.ajay.dto.ResponseDTO;
import com.ajay.repo.PostRepo;
import com.ajay.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/createNewPost")
    public ResponseEntity<?> createNewPost(@RequestBody RequestDTO request) {
        Object responseDTO = postService.createPost(request);
        if (responseDTO instanceof Map<?, ?>) {
            Map<?, ?> responseMap = (Map<?, ?>) responseDTO;
            if (responseMap.containsKey("error")) {
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
