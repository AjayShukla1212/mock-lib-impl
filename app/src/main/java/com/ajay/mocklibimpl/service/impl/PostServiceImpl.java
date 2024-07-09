package com.ajay.mocklibimpl.service.impl;

import com.ajay.mocklibimpl.constants.Constants;
import com.ajay.mocklibimpl.dao.Post;
import com.ajay.mocklibimpl.dto.GetPostDetails;
import com.ajay.mocklibimpl.dto.RequestDTO;
import com.ajay.mocklibimpl.dto.ResponseDTO;
import com.ajay.mocklibimpl.service.PostService;
import com.ajay.mocklibimpl.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;

    public PostServiceImpl(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Autowired
    private PostRepo postRepository;

    @Transactional
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public ResponseDTO createPost(RequestDTO request) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Post post = new Post();
            post.setName(request.getPostName());
            post.setContents(request.getPostContent());
            Post postResponse = postRepo.save(post);

           responseDTO.setDbPost(postResponse);

            RestTemplate restTemplate = new RestTemplate();
            String apiResponse = restTemplate.getForObject(Constants.WORLD_TIME_URL, String.class);
            responseDTO.setHttpOutbound(apiResponse);
            return responseDTO;

        } catch (Exception e) {
            responseDTO.setError(e.getMessage());
            return responseDTO;
        }
    }

    @Override
    public GetPostDetails getAllPosts() {
        GetPostDetails postDetails = new GetPostDetails();
        System.out.println("Before Repo");
//        postRepo.findAll();
        List<Post> posts = new ArrayList<>();
        System.out.println("Posts: " + posts);
        postDetails.setPostList(posts);

        RestTemplate restTemplate = new RestTemplate();
        String apiResponse = restTemplate.getForObject(Constants.WORLD_TIME_URL, String.class);

        postDetails.setHttpOutbound(apiResponse);
        return postDetails;
    }
}
