package com.sivalabs.mcpclient;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {
    private final ChatClient chatClient;

    public VideoController(ChatClient.Builder chatClientBuilder,
                           ToolCallbackProvider tools) {
        this.chatClient = chatClientBuilder
                .defaultSystem("Answer all questions with complete sentences.")
                .defaultToolCallbacks(tools)
                .build();
    }

    @PostMapping("/ask")
    public Answer ask(@RequestBody Question question) {
        return chatClient.prompt()
                .user(question.question())
                .call()
                .entity(Answer.class);
    }

    public record Question(String question) {
    }
    public record Answer(String answer) {
    }
}
