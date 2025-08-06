package com.musa.fraud_detection_backend.dto;

import lombok.Data;

@Data
public class FraudResponse {
    private double fraudProbability;
    private boolean flagged;
}
