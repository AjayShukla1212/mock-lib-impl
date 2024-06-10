package com.ajay.mocklibimpl.dto;

import com.ajay.mocklibimpl.dao.Post;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDTO {
    @JsonProperty(value = "db_post")
    private Post dbPost;
    @JsonProperty(value = "http_outbound")
    private String httpOutbound;
    @JsonProperty(value = "error")
    private String error;

    public ResponseDTO() {
    }

    public ResponseDTO(Post dbPost, String httpOutbound, String error) {
        this.dbPost = dbPost;
        this.httpOutbound = httpOutbound;
        this.error = error;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
