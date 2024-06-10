package com.ajay.hypertest.service;

import com.ajay.hypertest.dto.RequestDTO;
import com.ajay.hypertest.dto.ResponseDTO;

public interface PostService {
    ResponseDTO createPost(RequestDTO request);
}
