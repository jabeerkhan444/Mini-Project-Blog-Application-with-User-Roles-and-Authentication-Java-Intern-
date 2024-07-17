package com.model;

public class Post {
    private int id;
    private String title;
    private String content;
    private String filePath;
    private int userId;

    // Default constructor
    public Post() {}

    // Constructor with parameters
    public Post(int id, String title, String content, String filePath, int userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.filePath = filePath;
        this.userId = userId;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
