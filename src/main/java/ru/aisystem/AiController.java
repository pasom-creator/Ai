package ru.aisystem;

import ru.aisystem.service.AiResult;
import ru.aisystem.service.AiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/api/sentiment")
    public SentimentResponse sentiment(@RequestParam("text") String text) {
        AiResult result = aiService.analyze(text);
        return new SentimentResponse(result.sentiment(), result.confidence());
    }

    public record SentimentResponse(String sentiment, double confidence) {
    }
}
