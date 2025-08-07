package com.musa.fraud_detection_backend.service;

import com.musa.fraud_detection_backend.dto.FraudResponse;
import com.musa.fraud_detection_backend.dto.TransactionDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class FraudScoringService {

    private final WebClient webClient;

    public FraudScoringService(@Value("${fraud.ml.url}") String mlUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(mlUrl)
                .defaultHeader(MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public FraudResponse scoreTransaction(TransactionDto tx) {
        return webClient.post()
                .uri("") // base URL already includes the endpoint
                .bodyValue(tx)
                .retrieve()
                .bodyToMono(FraudResponse.class)
                .block();
    }
}
