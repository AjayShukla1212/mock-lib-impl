package com.ajay.mocklibimpl.dto;

import com.ajay.mocklibimpl.dao.Post;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class GetPostDetails {
    @JsonProperty(value = "post_list")
    private List<Post> postList;
    @JsonProperty(value = "http_outbound")
    private String httpOutbound;

    public List<Post> getPostList() {
        return postList;
    }

    public GetPostDetails() {
    }

    public String getHttpOutbound() {
        return httpOutbound;
    }

    public GetPostDetails(List<Post> postList, String httpOutbound) {
        this.postList = postList;
        this.httpOutbound = httpOutbound;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public void setHttpOutbound(String httpOutbound) {
        this.httpOutbound = httpOutbound;
    }
}

