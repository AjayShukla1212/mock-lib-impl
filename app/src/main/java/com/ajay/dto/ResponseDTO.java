package com.ajay.dto;

import com.ajay.dao.Post;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDTO {
    @JsonProperty(value = "db_post")
    private Post dbPost;
    @JsonProperty(value = "http_outbound")
    private String httpOutbound;

    public ResponseDTO() {
    }

    public ResponseDTO(Post dbPost, String httpOutbound) {
        this.dbPost = dbPost;
        this.httpOutbound = httpOutbound;
    }

    public Post getDbPost() {
        return dbPost;
    }

    public void setDbPost(Post dbPost) {
        this.dbPost = dbPost;
    }

    public String getHttpOutbound() {
        return httpOutbound;
    }

    public void setHttpOutbound(String httpOutbound) {
        this.httpOutbound = httpOutbound;
    }
}
