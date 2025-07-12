package org.badis.bdccai.controllers;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AIAgentController {

    private ChatClient chatClient;

    public AIAgentController(ChatClient.Builder builder, ChatMemory chatMemory) {
        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultAdvisors(
                        MessageChatMemoryAdvisor
                                .builder(chatMemory)
                                .build())
                .build();
    }

    @GetMapping("/chat")
    public String chat(String message) {

        List<Message> examples = List.of(
                new UserMessage("Comment tu t'appelles ?"),
                new AssistantMessage("Je m'appelle ChatGPT")
                );

        return chatClient.prompt()
                .messages(examples)
                .user(message)
                .call()
                .content();
    }
}
