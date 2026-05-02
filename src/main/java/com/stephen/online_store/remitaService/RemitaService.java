package com.stephen.online_store.remitaService;

import com.stephen.online_store.entity.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Service
public class RemitaService {

    private final RestTemplate restTemplate;

    public RemitaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String extractRRR(String response) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response);

            return node.get("RRR").asString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse RRR", e);
        }
    }

    public String generateRRR(Order order) {

        String url = "https://remita-api-url";

        Map<String , Object> body = new HashMap<>();
        body.put("serviceTypeId", "");
        body.put("amount", order.getAmount());
        body.put("orderId", order.getOrderNumber());
        body.put("payerName", order.getPayerName());
        body.put("payerEmail", order.getPayerEmail());

        String response = restTemplate.postForObject(url, body, String.class);
        System.out.println(response);

        String rrr = extractRRR(response);

        return rrr;
    }

}
