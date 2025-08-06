package com.musa.fraud_detection_backend.controller;

import com.musa.fraud_detection_backend.dto.FraudResponse;
import com.musa.fraud_detection_backend.dto.TransactionDto;
import com.musa.fraud_detection_backend.service.FraudScoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final FraudScoringService fraudService;

    @PostMapping("/score")
    public FraudResponse score(@RequestBody TransactionDto tx) {
        return fraudService.scoreTransaction(tx);
    }
}
