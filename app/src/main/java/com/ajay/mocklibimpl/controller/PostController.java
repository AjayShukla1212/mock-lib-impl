package com.ajay.mocklibimpl.controller;


import com.ajay.mocklibimpl.dto.RequestDTO;
import com.ajay.mocklibimpl.dto.ResponseDTO;
import com.ajay.mocklibimpl.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/createNewPost")
    public ResponseEntity<?> createNewPost(@RequestBody RequestDTO request) {
        ResponseDTO responseDTO = postService.createPost(request);
        if (responseDTO.getError()!=null) {
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
