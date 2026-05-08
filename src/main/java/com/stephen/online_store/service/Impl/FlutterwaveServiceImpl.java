package com.stephen.online_store.service.Impl;

import com.stephen.online_store.service.FlutterwaveService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class FlutterwaveServiceImpl implements FlutterwaveService {

    @Value("${secret-key}")
    private String secretKey;

    @Value("${base-url}")
    private String baseUrl;

    @Override
    public String createPayment(String email, String name, double amount, String txRef) {

        RestTemplate restTemplate = new RestTemplate();

        String url = baseUrl + "/payments";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(secretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = String.format("""
                {
                    "tx_ref": "%s",
                    "amount": "%s",
                    "currency": "NGN",
                    "redirect_url": "http://localhost:8080/api/payment/callback",
                    "customer": {
                        "email": "%s",
                        "name": "%s"
                    }
                }
                """, txRef, amount, email, name);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);

        Map data = (Map) response.getBody().get("data");

        return (String) data.get("link"); //this is what i redirect to
    }

    @Override
    public Map<String, Object> verifyPayment(String transactionId) {
        RestTemplate restTemplate = new RestTemplate();

        String url = baseUrl + "/transactions/" + transactionId + "/verify";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(secretKey);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        return (Map<String, Object>) response.getBody().get("data");

    }
}
