package com.sivalabs.mcpclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.*;

@RestController
public class VideoController {
    private static final Logger log = LoggerFactory.getLogger(VideoController.class);
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
        var answer = chatClient.prompt()
                .user(question.question())
                .call()
                .entity(Answer.class);
        log.debug("Answer from AI: {}", answer);
        String markdownAnswer = formatResponse(question, answer);
        log.debug("Markdown formatted answer: {}", markdownAnswer);
        var htmlResponse = MarkdownHelper.toHTML(markdownAnswer);
        log.debug("HTML formatted answer: {}", htmlResponse);
        return new Answer(htmlResponse);
    }

    private String formatResponse(Question question, Answer answer) {
        var prompt = """
                Following are the question and answer:
                
                Question: {question}
                
                Answer: {answer}
                
                Answer is in JSON format.
                
                Format the answer in plain text with Markdown syntax and return only the formatted response.
                """;
        return chatClient
                .prompt()
                .user(u -> u.text(prompt)
                        .param("question", question.question())
                        .param("answer", answer.answer())
                )
                .call()
                .content();
    }

    public record Question(String question) {
    }
    public record Answer(String answer) {
    }
}
