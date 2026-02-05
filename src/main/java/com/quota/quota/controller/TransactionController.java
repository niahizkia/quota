package com.quota.quota.controller;

import com.quota.quota.dto.*;
import com.quota.quota.entity.Quota;
import com.quota.quota.entity.Transaction;
import com.quota.quota.service.QuotaService;
import com.quota.quota.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quota/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private QuotaService quotaService;

    @GetMapping("/{tranId}")
    public ResponseDTO getTranById(@PathVariable String tranId) {
        ResponseDTO response = new ResponseDTO("00", "Success", transactionService.getTranDetails(tranId));
        return response;
    }

    @PostMapping("/create")
    public ResponseDTO createTransaction(@RequestBody TransactionDTO request) {
        Integer quotaAvailable = quotaService.getAvailableQuota(request.getQuotaId());
        if (quotaAvailable >= 1) {
            ResponseDTO response = new ResponseDTO("00", "Success", request);
            Transaction savedTransaction = transactionService.createTransaction(request);
            return response;
        } else {

            return new ResponseDTO("500", "Error", "Ticket sudah habis.");
        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
    }
    @DeleteMapping("/{id}")
    public ResponseDTO deleteTransaction(@PathVariable String id) {
        transactionService.deleteTransaction(id);
        return new ResponseDTO("00", "Success", "No content to display.");
    }
}
