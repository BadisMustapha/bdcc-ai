package org.badis.bdccai.controllers;


import org.badis.bdccai.outputs.Movie;
import org.badis.bdccai.outputs.MovieList;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIAgentStructuredController {

    private ChatClient chatClient;

    public AIAgentStructuredController(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }


    @GetMapping("/askAgent")
    public MovieList chat(String message) {

        String systemMessage = """
            Vous etes un expert dans le domaine du cinèma,
            Repondez à la question de l'utilisateur à ce propos
            
        """;

        return chatClient.prompt()
                .system(systemMessage)
                .user(message)
                .call()
                .entity(MovieList.class);
    }
}
