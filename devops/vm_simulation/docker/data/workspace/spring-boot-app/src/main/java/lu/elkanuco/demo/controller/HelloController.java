package lu.elkanuco.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @Value("${app.name:Spring Boot App}")
    private String appName;

    @Value("${app.environment:dev}")
    private String environment;

    @Value("${spring.datasource.url:not-configured}")
    private String dbUrl;

    @GetMapping("/")
    public Map<String, String> hello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello from " + appName);
        response.put("environment", environment);
        response.put("database", dbUrl);
        response.put("status", "running");
        return response;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        return response;
    }
}