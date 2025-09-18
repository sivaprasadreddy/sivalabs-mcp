package com.sivalabs.mcpserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VideoTools {
    private static final Logger log = LoggerFactory.getLogger(VideoTools.class);

    private final VideoRepository videoRepository;

    public VideoTools(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Tool(name = "getAllVideos", description = "Get all videos from SivaLabs")
    public List<YouTubeVideo> getAllVideos() {
        log.info("Getting all videos");
        return videoRepository.findAll();
    }

    @Tool(name = "searchVideos", description = "Search SivaLabs videos by title")
    public List<YouTubeVideo> searchVideos(String title) {
        log.info("Searching videos by title: {}", title);
        return videoRepository.findByTitleContainingIgnoreCase(title);
    }

    @Tool(name = "getVideoByTitle", description = "Get a single video from SivaLabs by title")
    public YouTubeVideo getVideoByTitle(String title) {
        log.info("Getting video by title: {}", title);
        return videoRepository.findByTitle(title).orElse(null);
    }

}
