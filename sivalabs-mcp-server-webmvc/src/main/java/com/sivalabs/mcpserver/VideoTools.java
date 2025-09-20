package com.sivalabs.mcpserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class VideoTools {
    private static final Logger log = LoggerFactory.getLogger(VideoTools.class);

    private final VideoRepository videoRepository;

    public VideoTools(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @McpTool(name = "get_all_videos", description = "Get all videos from SivaLabs")
    public Videos getAllVideos() {
        log.info("Getting all videos");
        return new Videos(videoRepository.findAll());
    }

    @McpTool(name = "search_videos", description = "Search SivaLabs videos by title")
    public Videos searchVideos(String title) {
        log.info("Searching videos by title: {}", title);
        return new Videos(videoRepository.findByTitleContainingIgnoreCase(title));
    }

    @McpTool(name = "get_video_by_title", description = "Get a single video from SivaLabs by title")
    public YouTubeVideo getVideoByTitle(String title) {
        log.info("Getting video by title: {}", title);
        return videoRepository.findByTitle(title).orElse(null);
    }

}
