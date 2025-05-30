package com.sivalabs.mcpclient;

import java.util.Objects;


public class Video {
    private Long id;
    private String title;
    private String url;

    public Video() {
    }

    public Video(String title, String url) {
        this.title = title;
        this.url = url;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video course = (Video) o;
        return Objects.equals(id, course.id) &&
               Objects.equals(title, course.title) &&
               Objects.equals(url, course.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, url);
    }

    @Override
    public String toString() {
        return "Video{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", url='" + url + '\'' +
               '}';
    }
}
