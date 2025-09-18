package com.sivalabs.youtube;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    static final String apiKey = System.getenv("YOUTUBE_DATA_API_KEY");
    static final ObjectMapper objectMapper = getObjectMapper();

    public static void main(String[] args) throws Exception {
        //String channelId = "UC7yMHBNLA1AnVfy_beGJGqg";
        String handle = "sivalabs";
        generateAllVideosJsonFile(handle);
        generateSearchVideosJsonFile(handle);
    }

    static void generateAllVideosJsonFile(String handle) throws Exception {
        YouTubeService youTubeService = new YouTubeService(apiKey, "youtube-api");
        Videos videos = youTubeService.getAllVideosFromChannel(handle);
        String json = objectMapper.writeValueAsString(videos);
        Files.writeString(Path.of("videos.json"), json);
    }

    static void generateSearchVideosJsonFile(String handle) throws Exception {
        YouTubeService youTubeService = new YouTubeService(apiKey, "youtube-api");
        Videos videos = youTubeService.searchVideos(handle, "modulith", 10);
        String json = objectMapper.writeValueAsString(videos);
        Files.writeString(Path.of("videos-search.json"), json);
    }

    static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return objectMapper;
    }
}