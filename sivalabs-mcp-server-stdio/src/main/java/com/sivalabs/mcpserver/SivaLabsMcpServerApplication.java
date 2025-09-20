package com.sivalabs.mcpserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.modelcontextprotocol.server.McpServerFeatures.SyncPromptSpecification;
import io.modelcontextprotocol.spec.McpSchema;
import io.modelcontextprotocol.spec.McpSchema.*;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SivaLabsMcpServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SivaLabsMcpServerApplication.class, args);
	}

	@Bean
	ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		return objectMapper;
	}

	@Bean
	public List<ToolCallback> toolCallbacks(VideoTools videoTools) {
		return List.of(ToolCallbacks.from(videoTools));
	}
}
