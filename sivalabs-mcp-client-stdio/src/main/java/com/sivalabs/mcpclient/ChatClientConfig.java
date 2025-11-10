package com.sivalabs.mcpclient;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ChatClientConfig {

    @Bean
    ChatClient chatClient(ChatClient.Builder builder,
                                ToolCallbackProvider tools) {
        return builder
                .defaultSystem("Answer all questions with complete sentences.")
                .defaultToolCallbacks(tools)
                .build();
    }
}