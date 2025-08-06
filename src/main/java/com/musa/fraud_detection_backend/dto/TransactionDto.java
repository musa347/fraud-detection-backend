package com.musa.fraud_detection_backend.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private int step;
    private String type;
    private double amount;
    private double oldbalanceOrg;
    private double newbalanceOrig;
    private double oldbalanceDest;
    private double newbalanceDest;
}
