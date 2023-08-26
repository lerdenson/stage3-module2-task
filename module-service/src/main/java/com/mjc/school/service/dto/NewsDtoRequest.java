package com.mjc.school.service.dto;

public class NewsDtoRequest {
    private long id;
    private String title;
    private String content;
    private long authorId;

    public NewsDtoRequest(long id, String title, String content, long authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

    public NewsDtoRequest(String title, String content, long authorId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getAuthorId() {
        return authorId;
    }
}
