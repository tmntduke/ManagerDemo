package com.tmnt.Entity;

/**
 * Created by tmnt on 2015/12/28.
 */
public class NewInfo {
    private String author;
    private String title;
    private String text;
    private String image;

    public NewInfo(String author, String image, String text, String title) {
        this.author = author;
        this.image = image;
        this.text = text;
        this.title = title;
    }

    public NewInfo() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "NewInfo{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
