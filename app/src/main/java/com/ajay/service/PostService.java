package com.ajay.service;


import com.ajay.dto.RequestDTO;
import com.ajay.dto.ResponseDTO;

public interface PostService {
    ResponseDTO createPost(RequestDTO request);
}
