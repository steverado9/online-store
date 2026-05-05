package com.stephen.online_store.remitaService;

import com.stephen.online_store.entity.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;


@Service
public class RemitaService {

    @Value("${merchantId}")
    private String merchantId;

    @Value("${apiKey}")
    private String apiKey;

    @Value("${serviceTypeId}")
    private String serviceTypeId;

    @Value("${secretKey}")
    private String secretKey;

    @Value("${publicKey}")
    private String publicKey;

    @Value("${baseUrl}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public RemitaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String extractRRR(String response) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response);

            return node.get("RRR").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse RRR", e);
        }
    }

    private String sha512 (String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder hex = new StringBuilder();
            for (byte b : bytes) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }

    public String generateRRR(Order order) {

        String url = baseUrl;
        String amount = String.valueOf(order.getAmount());
        String orderId = order.getOrderNumber();

        // Generate hash
        String hashString = merchantId + serviceTypeId + orderId + amount + apiKey;
        String hash = sha512(hashString);

        Map<String , Object> body = new HashMap<>();
        body.put("serviceTypeId", serviceTypeId);
        body.put("amount", amount);
        body.put("orderId", orderId);
        body.put("payerName", order.getPayerName());
        body.put("payerEmail", order.getPayerEmail());
        body.put("description", "Payment for order");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.set(
                "Authorization",
                "remitaConsumerKey=" + merchantId + ",remitaConsumerToken=" + hash
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        System.out.println("RAW RESPONSE: " + response.getBody());

        String rrr = extractRRR(response.getBody());

        return rrr;
    }

}
