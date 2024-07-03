package com.ajay.mocklibimpl.service;

import com.ajay.mocklibimpl.dto.GetPostDetails;
import com.ajay.mocklibimpl.dto.RequestDTO;
import com.ajay.mocklibimpl.dto.ResponseDTO;

public interface PostService {
    ResponseDTO createPost(RequestDTO request);
    GetPostDetails getAllPosts();
}
