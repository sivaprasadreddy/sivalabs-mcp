package com.sivalabs.youtube;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;

import java.time.Duration;
import java.util.*;

public class YouTubeService {
    private static final int MAX_RESULTS = 50;

    private final String apiKey;
    private final YouTube youtube;

    public YouTubeService(
            String apiKey,
            String applicationName) {
        this.apiKey = apiKey;
        this.youtube = getYouTube(applicationName);
    }

    private YouTube getYouTube(String applicationName) {
        try {
            return new YouTube.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    GsonFactory.getDefaultInstance(),
                    request -> {
                    }
            ).setApplicationName(applicationName).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Videos getAllVideosFromChannel(String handle) throws Exception {
        List<String> videoIds = getAllVideoIdsFromChannel(handle);
        return getVideos(videoIds);
    }

    public Videos searchVideos(String handle, String query, long maxResults) throws Exception {
        String channelId = getChannelIdFromHandle(handle);
        YouTube.Search.List searchRequest = youtube.search()
                .list(Collections.singletonList("snippet"))
                .setChannelId(channelId)
                .setQ(query)
                .setType(Collections.singletonList("video"))
                .setOrder("date")
                .setMaxResults(Math.min(maxResults, MAX_RESULTS))
                .setKey(apiKey);

        SearchListResponse searchResponse = searchRequest.execute();
        List<SearchResult> searchResults = searchResponse.getItems();

        List<String> videoIds = searchResults.stream()
                .map(result -> result.getId().getVideoId())
                .toList();
        return getVideos(videoIds);
    }

    public Optional<VideoInfo> getVideo(String videoId) throws Exception {
        return getVideos(List.of(videoId)).videos().stream().findFirst();
    }

    public Videos getVideos(List<String> videoIds) throws Exception {
        List<VideoInfo> videoInfoList = new ArrayList<>();
        List<List<String>> lists = chunkArrayList(videoIds, MAX_RESULTS);
        for (List<String> list : lists) {
            YouTube.Videos.List detailsRequest = youtube.videos()
                    .list(List.of("snippet,contentDetails"))
                    .setId(list)
                    .setKey(apiKey);

            VideoListResponse detailsResponse = detailsRequest.execute();

            for (Video video : detailsResponse.getItems()) {
                VideoInfo videoInfo = new VideoInfo(
                        video.getSnippet().getTitle(),
                        video.getSnippet().getDescription(),
                        video.getSnippet().getPublishedAt().toString(),
                        parseDuration(video.getContentDetails().getDuration()),
                        "https://www.youtube.com/watch?v=" + video.getId()
                );
                videoInfoList.add(videoInfo);
            }
        }
        return new Videos(videoInfoList);
    }

    public List<String> getAllVideoIdsFromChannel(String handle) throws Exception {
        String uploadsPlaylistId = getUploadsPlaylistId(handle);

        List<String> videoIds = new ArrayList<>();
        String nextPageToken = null;

        do {
            YouTube.PlaylistItems.List request = youtube.playlistItems()
                    .list(Collections.singletonList("snippet"))
                    .setPlaylistId(uploadsPlaylistId)
                    .setMaxResults((long) MAX_RESULTS)
                    .setPageToken(nextPageToken)
                    .setKey(apiKey);

            PlaylistItemListResponse response = request.execute();
            for (PlaylistItem item : response.getItems()) {
                videoIds.add(item.getSnippet().getResourceId().getVideoId());
            }

            nextPageToken = response.getNextPageToken();
        } while (nextPageToken != null);

        return videoIds;
    }

    public String getChannelIdFromHandle(String handle) throws Exception {
        YouTube.Channels.List request = youtube.channels()
                .list(Collections.singletonList("id"))
                .setForHandle(handle)
                .setKey(apiKey);

        ChannelListResponse response = request.execute();
        if (response.getItems().isEmpty()) {
            throw new RuntimeException("Channel not found for handle: " + handle);
        }

        return response.getItems().getFirst().getId();
    }

    private String getUploadsPlaylistId(String handle) throws Exception {
        YouTube.Channels.List request = youtube.channels()
                .list(Collections.singletonList("contentDetails"))
                //.setId(Collections.singletonList(channelId))
                .setForHandle(handle)
                .setKey(apiKey);

        ChannelListResponse response = request.execute();
        if (response.getItems().isEmpty()) {
            throw new RuntimeException("Channel not found: " + handle);
        }

        return response.getItems().getFirst()
                .getContentDetails()
                .getRelatedPlaylists()
                .getUploads();
    }

    private List<List<String>> chunkArrayList(List<String> arrayToChunk, int chunkSize) {
        List<List<String>> chunkList = new ArrayList<>();
        for (int i = 0; i < arrayToChunk.size(); i += chunkSize) {
            int end = Math.min(i + chunkSize, arrayToChunk.size());
            chunkList.add(arrayToChunk.subList(i, end));
        }
        return chunkList;
    }

    private String parseDuration(String isoDuration) {
        Duration duration = Duration.parse(isoDuration);
        long minutes = duration.toMinutes();
        long seconds = duration.minusMinutes(minutes).getSeconds();
        return String.format("%02d:%02d", minutes, seconds);
    }
}
