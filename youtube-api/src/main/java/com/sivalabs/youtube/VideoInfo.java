package com.sivalabs.youtube;


import com.fasterxml.jackson.annotation.JsonIgnore;

public record VideoInfo(String title,
                        @JsonIgnore
                        String description,
                        String publishedAt,
                        String duration,
                        String url) {}