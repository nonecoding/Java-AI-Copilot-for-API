// OpenAIClient.java
package org.ai.aicopilotforapi.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;

import java.util.*;

@Component
public class OpenAIClient {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public OpenAIClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Flux<String> generateCodeStream(String prompt) {
        HttpClient httpClient = HttpClient.create()
                .proxy(proxy -> proxy.type(ProxyProvider.Proxy.HTTP)
                        .host("127.0.0.1")
                        .port(7890)); // 改成你本地代理端口

        WebClient webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(apiUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-4o-mini");
        body.put("stream", true); // 开启流式
        body.put("messages", List.of(Map.of("role", "user", "content", prompt)));

        return webClient.post()
                .bodyValue(body)
                .accept(MediaType.TEXT_EVENT_STREAM, MediaType.APPLICATION_NDJSON, MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(String.class)
                .flatMap(chunk -> Mono.justOrEmpty(extractContentFromStreamChunk(chunk)))
                .filter(Objects::nonNull);

    }

    // 你需要实现 extractContentFromStreamChunk 方法来提取每段的 content
    private String extractContentFromStreamChunk(String chunk) {
        // 解析 JSON 并提取 content 字段
        // 例如：{"choices":[{"delta":{"content":"hello"}}]}
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(chunk);
            JsonNode choices = node.get("choices");
            if (choices != null && choices.isArray() && choices.size() > 0) {
                JsonNode delta = choices.get(0).get("delta");
                if (delta != null && delta.has("content")) {
                    return delta.get("content").asText();
                }
            }
        } catch (Exception e) {
            // 忽略解析失败
        }
        return null;
    }
}