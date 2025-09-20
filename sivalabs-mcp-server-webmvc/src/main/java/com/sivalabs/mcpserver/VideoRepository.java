package com.sivalabs.mcpserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VideoRepository {
    private static final Logger log = LoggerFactory.getLogger(VideoRepository.class);

    private final ObjectMapper objectMapper;
    private List<YouTubeVideo> videos;

    public VideoRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    void init() {
        try (InputStream inputStream = new ClassPathResource("/videos.json").getInputStream()) {
            Videos videos = objectMapper.readValue(inputStream, Videos.class);
            this.videos = videos.videos();
        } catch (IOException e) {
            log.error("Failed to initialize videos data", e);
        }
        if(this.videos == null) {
            log.warn("Videos not found. Loading empty list");
            this.videos = new ArrayList<>();
        }
        log.info("Videos loaded: {}", this.videos.size());
    }

    public List<YouTubeVideo> findAll() {
        return videos;
    }

    public Optional<YouTubeVideo> findByTitle(String title) {
        return videos.stream().filter(video -> video.title().equalsIgnoreCase(title)).findFirst();
    }

    public List<YouTubeVideo> findByTitleContainingIgnoreCase(String query) {
        return videos.stream().filter(video -> video.title().toLowerCase().contains(query.toLowerCase())).toList();
    }
}