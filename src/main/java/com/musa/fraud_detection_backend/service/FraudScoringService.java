package com.musa.fraud_detection_backend.service;

import com.musa.fraud_detection_backend.dto.FraudResponse;
import com.musa.fraud_detection_backend.dto.TransactionDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class FraudScoringService {

    private final WebClient webClient;

    public FraudScoringService(@Value("${fraud.ml.url}") String mlurl) {
        System.out.println("mlUrl: " + mlurl);
        this.webClient = WebClient.create(mlurl);
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
