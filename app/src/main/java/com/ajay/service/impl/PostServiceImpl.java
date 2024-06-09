package com.ajay.service.impl;

import com.ajay.constants.Constants;
import com.ajay.dao.Post;
import com.ajay.dto.RequestDTO;
import com.ajay.dto.ResponseDTO;
import com.ajay.repo.PostRepo;
import com.ajay.service.PostService;
import netscape.javascript.JSObject;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;

    public PostServiceImpl(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public Object createPost(RequestDTO request) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Post post = new Post();
            post.setName(request.getPostName());
            post.setContents(request.getPostContent());
            post = postRepo.save(post);

           responseDTO.setDbPost(post);

            RestTemplate restTemplate = new RestTemplate();
            String apiResponse = restTemplate.getForObject(Constants.WORLD_TIME_URL, String.class);
            responseDTO.setHttpOutbound(apiResponse);
            return responseDTO;
        } catch (Exception e) {
           // response.put("error", e.getMessage());
            Map<String, String> errorObject = new HashMap<>();
            errorObject.put("error", e.getMessage());
            return errorObject;
        }
    }
}
