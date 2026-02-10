package com.quota.quota.controller;

import com.quota.quota.dto.ResponseDTO;
import com.quota.quota.dto.TransactionQuotaDTO;
import com.quota.quota.service.QuotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/quota")
@RequiredArgsConstructor
public class QuotaController {

    private final QuotaService quotaService;

    @GetMapping("/{quotaId}")
    public ResponseDTO getById(@PathVariable UUID quotaId) {

        ResponseDTO response = new ResponseDTO("00", "Success",
                quotaService.getQuotaDetails(quotaId));
        return response;
    }

    @GetMapping("/transactions/{quotaId}")
    public ResponseDTO getAllTranByQuotaId(@PathVariable UUID quotaId) {
        TransactionQuotaDTO message = new TransactionQuotaDTO(quotaService.getQuotaDetails(quotaId),
                quotaService.getAllTranByQuotaId(quotaId));
        ResponseDTO response = new ResponseDTO("00", "Success", message);
        return response;
    }
}
