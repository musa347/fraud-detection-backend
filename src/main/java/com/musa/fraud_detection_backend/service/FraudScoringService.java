package com.musa.fraud_detection_backend.service;

import com.musa.fraud_detection_backend.dto.FraudResponse;
import com.musa.fraud_detection_backend.dto.TransactionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class FraudScoringService {

    private static final Logger logger = LoggerFactory.getLogger(FraudScoringService.class);
    private final WebClient webClient;

    public FraudScoringService(@Value("${fraud.ml.url:https://fraud-detection-backend-9ssq.onrender.com/score-transaction}") String mlUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(mlUrl)
                .defaultHeader(MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public FraudResponse scoreTransaction(TransactionDto tx) {
        logger.info("Sending transaction to FastAPI: {}", tx);
        try {
            Mono<FraudResponse> responseMono = webClient.post()
                    .uri("") // Base URL includes endpoint
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(tx)
                    .retrieve()
                    .bodyToMono(FraudResponse.class)
                    .doOnError(error -> logger.error("Error calling FastAPI: {}", error.getMessage()));
            FraudResponse response = responseMono.block();
            if (response == null) {
                throw new RuntimeException("FastAPI response is null");
            }
            logger.info("Received response from FastAPI: {}", response);
            return response;
        } catch (Exception e) {
            logger.error("Failed to score transaction: {}", e.getMessage());
            throw new RuntimeException("Failed to communicate with FastAPI: " + e.getMessage());
        }
    }
}