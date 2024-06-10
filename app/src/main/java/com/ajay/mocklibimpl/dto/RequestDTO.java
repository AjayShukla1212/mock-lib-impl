package com.ajay.mocklibimpl.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestDTO {
    @JsonProperty(value = "post_name")
    private String postName;

    @JsonProperty(value = "post_contents")
    private String postContent;

    public RequestDTO() {
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public RequestDTO(String postName, String postContent) {
        this.postName = postName;
        this.postContent = postContent;
    }

    public String getPostName() {
        return postName;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }
}
