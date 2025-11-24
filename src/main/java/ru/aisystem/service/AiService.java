package ru.aisystem.service;

import ru.aisystem.inference.AiModel;
import ru.aisystem.inference.AiModelLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AiService {

    private static final Pattern TOKEN_PATTERN = Pattern.compile("\\p{L}+");
    private static final double POSITIVE_THRESHOLD = 0.6d;
    private static final double NEGATIVE_THRESHOLD = 0.4d;

    private final AiModel model;

    public AiService(ObjectMapper objectMapper) {
        this.model = AiModelLoader.load(objectMapper, "/model/ai-model.json");
    }

    public AiResult analyze(String text) {
        Map<String, Integer> features = tokenize(text);
        if (features.isEmpty()) {
            return new AiResult("neutral", 0.5d);
        }
        double probability = model.predictProbability(features);
        String label = label(probability);
        return new AiResult(label, probability);
    }

    private static Map<String, Integer> tokenize(String text) {
        Map<String, Integer> frequencies = new HashMap<>();
        Matcher matcher = TOKEN_PATTERN.matcher(text.toLowerCase(Locale.ROOT));
        while (matcher.find()) {
            String token = matcher.group();
            frequencies.merge(token, 1, Integer::sum);
        }
        return frequencies;
    }

    private static String label(double probability) {
        if (probability >= POSITIVE_THRESHOLD) {
            return "positive";
        }
        if (probability <= NEGATIVE_THRESHOLD) {
            return "negative";
        }
        return "neutral";
    }
}
