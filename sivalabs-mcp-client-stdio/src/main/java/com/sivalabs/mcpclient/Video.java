package com.sivalabs.mcpclient;

import java.util.Objects;

public class Video {
    private Long id;
    private String title;
    private String url;
    private String publishedAt;
    private String duration;

    public Video() {
    }

    public Video(String title, String url, String publishedAt, String duration) {
        this.title = title;
        this.url = url;
        this.publishedAt = publishedAt;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(id, video.id)
                && Objects.equals(url, video.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url);
    }
}
