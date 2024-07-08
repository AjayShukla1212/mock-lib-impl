package com.ajay.mocklibimpl.agent;


public class MockData {
    private String id;
    private String name;
    private String contents;

    public MockData(String id, String name, String contents) {
        this.id = id;
        this.name = name;
        this.contents = contents;
    }

    public MockData() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
