package com.sivalabs.mcp;

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
	public List<ToolCallback> toolCallbacks(CourseService courseService) {
		return List.of(ToolCallbacks.from(courseService));
	}

	@Bean
	public List<SyncPromptSpecification> prompts() {
		var prompt = new Prompt("SivaLabs Courses", "Get courses from SivaLabs", List.of());

		var promptSpecification = new SyncPromptSpecification(prompt, (exchange, getPromptRequest) -> {
			var userMessage = new PromptMessage(Role.USER, new TextContent("What are the courses offered by SivaLabs?"));
			return new McpSchema.GetPromptResult("A list of SivaLabs courses", List.of(userMessage));
		});

		return List.of(promptSpecification);
	}
}
