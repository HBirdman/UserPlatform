package aston.homework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FallbackController {

    private static final Logger log = LoggerFactory.getLogger(FallbackController.class);

    @GetMapping("/fallback/user")
    public Mono<Map<String, String>> userServiceFallback(ServerWebExchange exchange) {
        log.warn("UserService is unavailable! Request: {}", exchange.getRequest().getPath());

        Map<String, String> response = new HashMap<>();
        response.put("status", "503");
        response.put("error", "User Service is currently unavailable. Please try again later.");
        response.put("timestamp", Instant.now().toString());

        return Mono.just(response);
    }

    @GetMapping("/fallback/notification")
    public Mono<Map<String, String>> notificationServiceFallback(ServerWebExchange exchange) {
        log.warn("NotificationService is unavailable! Request: {}", exchange.getRequest().getPath());

        Map<String, String> response = new HashMap<>();
        response.put("status", "503");
        response.put("error",
                "Notification Service is temporarily unavailable. Your action was recorded but email was not sent.");
        response.put("timestamp", Instant.now().toString());

        return Mono.just(response);
    }
}