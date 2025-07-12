package org.badis.bdccai.controllers;


import org.badis.bdccai.outputs.CinModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MultiModalController {

    private ChatClient chatClient;

    @Value("classpath:images/cin.png")
    private Resource image;

    @Value("classpath:images/facture.png")
    private Resource factur;

    public MultiModalController(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @GetMapping("/describe")
    public CinModel describe() {
        return chatClient.prompt()
                .system("Donne moi une description de l'image ci-dessous:")
                .user(u->
                        u.text("Dècrire l'image ci-dessous")
                                .media(MediaType.IMAGE_PNG, image)
                )
                .call()
                .entity(CinModel.class);

    }

    @PostMapping("/describe2")
    public String describe2(String message, MultipartFile file) {
        return chatClient.prompt()
                .system("Donne moi une description de l'image ci-dessous:")
                .user(u->
                        u.text(message)
                                .media(MediaType.IMAGE_PNG, file.getResource())
                )
                .call()
                .content();

    }
}
