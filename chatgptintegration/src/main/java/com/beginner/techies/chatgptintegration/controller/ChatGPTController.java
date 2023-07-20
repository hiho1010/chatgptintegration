package com.beginner.techies.chatgptintegration.controller;

import com.beginner.techies.chatgptintegration.dto.ChatMessagePrompt;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatGPTController {

    @GetMapping("/getChat/{prompt}")
    public String getPrompt(@PathVariable String prompt) {
        // /v1/completion -> text-davinci-003, text-davinci-002, text-davinci-001, text-curie-001, text-babbage-001, text-ada-001

        OpenAiService service = new OpenAiService("");
        CompletionRequest completionRequest = CompletionRequest.builder().prompt(prompt).model("text-davinci-003")
                .echo(true).build();
        return service.createCompletion(completionRequest).getChoices().get(0).getText();
    }

    @PostMapping("/chat")
    public String getChatMessages(@RequestBody ChatMessagePrompt prompt) {
        // /v1/chat/completions -> gpt-4, gpt-4-0613, gpt-4-32k, gpt-4-32k-0613, gpt-3.5-turbo, gpt-3.5-turbo-0613, gpt-3.5-turbo-16k, gpt-3.5-turbo-16k-0613

        OpenAiService service = new OpenAiService("");
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder().messages(prompt.getChatMessage())
                .model("gpt-3.5-turbo").build();
        return service.createChatCompletion(completionRequest).getChoices().get(0).getMessage().getContent();
    }
}
