package com.ajay.hypertest.service.impl;

import com.ajay.hypertest.constants.Constants;
import com.ajay.hypertest.dao.Post;
import com.ajay.hypertest.dto.RequestDTO;
import com.ajay.hypertest.dto.ResponseDTO;
import com.ajay.hypertest.service.PostService;
import com.ajay.hypertest.repo.PostRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;

    public PostServiceImpl(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public ResponseDTO createPost(RequestDTO request) {
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
            responseDTO.setError(e.getMessage());
            return responseDTO;
        }
    }
}
