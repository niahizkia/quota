package com.quota.quota.controller;


import com.quota.quota.dto.QuotaSummaryDTO;
import com.quota.quota.dto.ResponseDTO;
import com.quota.quota.dto.TransactionDetailDTO;
import com.quota.quota.dto.TransactionQuotaDTO;
import com.quota.quota.service.QuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/quota")
public class QuotaController {
    @Autowired
    private QuotaService quotaService;

    @GetMapping("/{quotaId}")
    public ResponseDTO getById(@PathVariable String quotaId) {

        ResponseDTO response = new ResponseDTO("00", "Success",
                quotaService.getQuotaDetails(quotaId));
        return response;
    }

    @GetMapping("/transactions/{quotaId}")
    public ResponseDTO getAllTranByQuotaId(@PathVariable String quotaId) {
        TransactionQuotaDTO message = new TransactionQuotaDTO( quotaService.getQuotaDetails(quotaId), quotaService.getAllTranByQuotaId(quotaId));
        ResponseDTO response = new ResponseDTO("00", "Success", message);
        return response;
    }
}
